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

if [[ $(echo "ab-cd" | sed 's/-\([a-z]\)/\U\1/g') != "abCd" ]]; then
   echo "ERROR: incompatible version of sed detected." >&2
   exit 1;
fi

repoName="$(basename "$PWD")"

# sedCode(sedCmd)
function sedCode() {
  find . \( -path "./init.sh" -o -path "./init_headless.sh" -o -path "./.git/*" -o -path "./.gradle/*" \) -prune -o -type f -print0 | xargs -0 sed -i $1
}

# replaceInCode(text-to-replace, replacement)
function replaceInCode() {
  sedCode "s/$1/$2/g"
}

echo Removing test expectation
echo "Topologies:" > example-service/src/test/resources/kafka/streams/expected_topology.txt

echo Updating repo name
replaceInCode "aggregate-template" "$repoName"

echo Deleting Creek specific code
sedCode "/.*init:remove.*/d"
rm -rf system-tests/src/system-test/example-suite

echo Tidying up
rm ./bootstrap.sh
rm ./.github/workflows/bootstrap.yml
