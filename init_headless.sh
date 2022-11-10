#!/bin/zsh
#
# Copyright 2022 Creek Contributors (https://github.com/creek-service)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

if [[ $(echo "ab-cd" | sed 's/-\([a-z]\)/\U\1/g') != "abCd" ]]; then
   echo "ERROR: incompatible version of sed detected." >&2
   echo "If on Mac, 'brew install gnu-sed' and add to path" >&2
   exit 1;
fi

serviceName="example-service"
serviceClass="ExampleServiceDescriptor"

while :; do
  case $1 in
    -h|--help)
        echo "$0 [-sn NAME] [-sc NAME] [-rn NAME] [-gn NAME] [-an NAME] [-rp NAME] [-mn NAME]"
        echo
        echo "   -sn, --service-name NAME       the name of the service module in the project."
        echo "                                  Default: $serviceName"
        echo "   -sc, --service-class NAME      the class name for the service descriptor."
        echo "                                  Default: $serviceClass"
        exit
        ;;
    -sn|--service-name)
        shift
        serviceName="$1"
        ;;
    -sc|--service-class)
        shift
        serviceClass="$1"
        ;;
    --)
        shift
        break
        ;;
    -?*)
        echo "WARN: Unknown option (ignored): $1" >&2
        ;;
    *)
        break
  esac

  shift
done

# sedCode(sedCmd)
function sedCode() {
  find . -type f -not \( -path "./init.sh" -o -path "./init_headless.sh" -o -path "*/.git/*" -o -path "*/.gradle/*" \) -print0 | xargs -0 sed -i "$1"
}

# replaceInCode(text-to-replace, replacement)
function replaceInCode() {
  sedCode "s/$1/$2/g"
}

# renamePackage(old-pkg-name, new-pkg-name)
function renamePackage() {
  # Update code:
  replaceInCode "$(echo "$1" | sed 's/\./\\./g')\." "$2."

  # Move code:
  oldBasePattern=$(echo "$1" | sed 's/\./\\\//g')
  oldBaseDir=$(echo "$1" | sed 's/\./\//g')
  newBaseDir=$(echo "$2" | sed 's/\./\//g')

find . -type f -path "*$oldBaseDir*" -not \( -path "./init.sh" -o -path "./init_headless.sh" -o -path "*/.git/*" -o -path "*/.gradle/*" \) -exec bash -c '
    newPath=${3/$1/$0}
    mkdir -p "$(dirname $newPath)"
    mv "$3" "$newPath"
    ' "$newBaseDir" "$oldBasePattern" "$oldBaseDir" {} \;
}

echo Prepare
find . -type d -empty -delete

echo Removing test expectation
echo "Topologies:" > example-service/src/test/resources/kafka/streams/expected_topology.txt

if [ "$serviceName" != "example-service" ]; then
  echo "Renaming service to: $serviceName"
  replaceInCode "example-service" "$serviceName"
  mv "example-service" "$serviceName"
fi

if [ "$serviceClass" != "ExampleServiceDescriptor" ]; then
  echo "Updating service descriptor to $serviceClass"
  replaceInCode "ExampleServiceDescriptor" "$serviceClass"
  mv "services/src/main/java/org/acme/example/services/ExampleServiceDescriptor.java" "services/src/main/java/org/acme/example/services/$serviceClass.java"
fi

echo Deleting Creek specific code
rm -rf system-tests/src/system-test/example-suite

echo Tidying up
rm ./init.sh
rm ./init_headless.sh
rm ./.github/workflows/bootstrap.yml
rm ./.github/workflows/test-init-script.yml
find . -type d -empty -delete
./gradlew clean format