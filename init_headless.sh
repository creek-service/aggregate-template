#!/bin/bash
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

if [ "$#" -ne 7 ]; then
    echo "Illegal number of parameters"
fi

serviceName=$1
serviceClass=$2
repoName=$3
groupName=$4
aggDesc=$5
rootPackage=$6
modNamePrefix=$7

# sedCode(sedCmd)
function sedCode() {
  find . \( -path "./init.sh" -o -path "./init_headless.sh" -o -path "./.git/*" -o -path "./.gradle/*" \) -prune -o -type f -print0 | xargs -0 sed -i $1
}

# replaceInCode(text-to-replace, replacement)
function replaceInCode() {
  sedCode "s/$1/$2/g"
}

# renamePackage(old-pkg-name, new-pkg-name)
function renamePackage() {
  # Update code:
  replaceInCode "$(echo $1 | sed 's/\./\\./g')\." "$2."

  # Move code:
  oldBasePattern=$(echo $1 | sed 's/\./\\\//g')
  oldBaseDir=$(echo $1 | sed 's/\./\//g')
  newBaseDir=$(echo $2 | sed 's/\./\//g')

  find . \( -path "./init.sh" -o -path "./init_headless.sh" -o -path "./.git/*" -o -path "./.gradle/*" \) -prune -o -type f -path "*$oldBaseDir*" -exec bash -c '
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
  echo Renaming service
  replaceInCode "example-service" "$serviceName"
  mv "example-service" "$serviceName"

  echo Updating service descsriptor
  replaceInCode "ExampleServiceDescriptor" "$serviceClass"
  mv "services/src/main/java/org/acme/example/services/ExampleServiceDescriptor.java" "services/src/main/java/org/acme/example/services/$serviceClass.java"
fi

echo Updating repo name
replaceInCode "aggregate-template" "$repoName"

echo Updating aggregate descsriptor
replaceInCode "ExampleAggregateDescriptor" "$aggDesc"
mv "api/src/main/java/org/acme/example/api/ExampleAggregateDescriptor.java" "api/src/main/java/org/acme/example/api/$aggDesc.java"

if [ "$rootPackage" != "org.acme.example" ]; then
  echo Updating root packages
  renamePackage "org.acme.example" "$rootPackage"
fi

if [ "$groupName" != "org.acme" ]; then
  echo Updating group name
  replaceInCode "org.acme" "$groupName"
fi

if [ "$modNamePrefix" != "example" ]; then
  echo Updating module names
  replaceInCode "example.mod" "$modNamePrefix"
fi

echo Deleting Creek specific code
sedCode "/.*init:remove.*/d"

echo Tidying up
find . -type d -empty -delete