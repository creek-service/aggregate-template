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

succinct=false
useDefaults=false
force=false

while :; do
  case $1 in
    -h|--help)
        echo $0 [-d] [-s]
        echo
        echo "   -d, --use-defaults   Use defaults for all questions (also enables -s)"
        echo "   -s, --succinct       Minimise output"
        echo "   -f, --force          Do not prompt for confirmation"
        exit
        ;;
    -s|--succint)
        succinct=true
        ;;
    -d|--use-defaults)
        useDefaults=true
        succinct=true
        ;;
    -f|--force)
        force=true
        ;;
    --)
        shift
        break
        ;;
    -?*)
        printf 'WARN: Unknown option (ignored): %s\n' "$1" >&2
        ;;
    *)
        break
  esac

  shift
done

if [ "$succinct" = false ]; then
  echo This script will customise a repository cloned from the tempalte.
  echo WARNING: only run this script once. If there are issues, revert all changes before running again.
  echo
  echo Please answer the following questions to control the customisation:
fi

if [ "$succinct" = false ]; then
  echo
  echo "The template contains a single service. Its name can be customised."
  echo "It is recommended the name ends in '-service'."
  echo "Additional services can be added by duplicating the service module."
fi
if [ "$useDefaults" = false ]; then
  read -p "Service module name [example-service]: " serviceName
fi
serviceName=${serviceName:-example-service}

if [ "$succinct" = false ]; then
  echo
  echo "The service descriptor is the class that forms the API of the service within the aggregate."
  echo "It is the class that other services in the aggregate will use to access its inputs and outputs."
  echo "The default name is based off the service name given above. Or you can provide a custom name."
fi
defaultServiceClass=$(echo $serviceName | sed 's/-\([a-z]\)/\U\1/g' | sed 's/^\([a-z]\)/\U\1/')Descriptor
if [ "$useDefaults" = false ]; then
  read -p "Service class name [$defaultServiceClass]: " serviceClass
fi
serviceClass=${serviceClass:-$defaultServiceClass}

if [ "$succinct" = false ]; then
  echo
  echo "What is the name of the cloned repository in GitHub?"
  echo "This name is used to ensure the links to repository resources in Github are correct."
  echo "The default is to root directory name of the local project."
  echo "Only alpha-numerics and the dash(-) character are currently supported"
fi
if [ "$useDefaults" = false ]; then
  read -p "New repository name [$(basename $PWD)]: " repoName
fi
repoName=${repoName:-$(basename $PWD)}

if [ "$succinct" = false ]; then
  echo
  echo "The artefact group name is the group name used when publishing artefacts."
  echo "The default is to leave it as 'org.acme', which is fine for hacking about."
fi
if [ "$useDefaults" = false ]; then
  read -p "Artefact group name [org.acme]: " groupName
fi
groupName=${groupName:-org.acme}

if [ "$succinct" = false ]; then
  echo
  echo "The aggregate descriptor is the class that forms the API of this aggregate."
  echo "It is the class that other aggregates will use to access its inputs and outputs."
  echo "The default name is based off the repository name. Or you can provide a custom name."
fi
defaultAggregateClass=$(echo $repoName | sed 's/-\([a-z]\)/\U\1/g' | sed 's/^\([a-z]\)/\U\1/')AggregateDescriptor
if [ "$useDefaults" = false ]; then
  read -p "Aggregate class name [$defaultAggregateClass]: " aggregateClass
fi
aggregateClass=${aggregateClass:-$defaultAggregateClass}

if [ "$succinct" = false ]; then
  echo
  echo "All the code in the template sits under a 'org.acme.example' root package."
  echo "The default is based of the group and repo names; '<group name>.<repo name>"
  echo "Or you can set a custom root package."
fi
defaultRootPackage=$groupName.$(echo $repoName | sed 's/-/./g')
if [ "$useDefaults" = false ]; then
  read -p "Root package name [$defaultRootPackage]: " rootPackage
fi
rootPackage=${rootPackage:-$defaultRootPackage}

if [ "$succinct" = false ]; then
  echo
  echo "The module name prefix is the prefix added to each JPMS module's name."
  echo "For example, given a prefix of 'bob', then the api module's name would be 'bob.api'."
  echo "This is only important if you're using the Java Platform's Module System, JPMS."
  echo "The default is based of the repo name."
fi
defaultModNamePrefix=$(echo $repoName | sed 's/-/./g')
if [ "$useDefaults" = false ]; then
  read -p "Module name prefix [$defaultModNamePrefix]: " modNamePrefix
fi
modNamePrefix=${modNamePrefix:-$defaultModNamePrefix}

if [ "$force" = false ]; then
  echo
  echo About to customise the repository using:
  echo Service module name: $serviceName
  echo Service class name: $serviceClass
  echo Repository name: $repoName
  echo Artefact Group name: $groupName
  echo Aggregate class name: $aggregateClass
  echo Root package name: $rootPackage
  echo Module name prefix: $modNamePrefix
  echo

  read -p "Are you sure? (y/n): " -n 1 -r
  if [[ ! $REPLY =~ ^[Yy]$ ]]
  then
      exit 1
  fi
fi

./init_headless.sh "$serviceName" "$serviceClass" "$repoName" "$groupName" "$aggregateClass" "$rootPackage" "$modNamePrefix"
