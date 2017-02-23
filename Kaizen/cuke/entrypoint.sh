#!/bin/bash

echo '-------------------------------'
echo '[INFO] entrypoint.sh started...'
echo '-------------------------------'

# Vars
jsonoutputfile=jsonoutput.json
xmloutputfile=../target/allure-results/$(uuidgen)-testsuite.xml
workdir=tests

# Deps
echo 'Checking node installed...'
if [[ $(which node | wc -l | xargs) < 1 ]]; then
    echo 'Please install 'node' or symlink node->nodejs and try again...'
    exit 1
fi

npm i

echo 'Checking cucumber installed...'
if [[ $(npm list -g 2>/dev/null | grep cucumber | wc -l | xargs) < 1 ]]; then
    echo 'Please install cucumber (npm install -g cucumber)...'
    exit 1
fi

# Go to tests dir and execute the runner.
echo '[INFO] Changing to work dir...'
cd ${workdir}

# Run cucumber tests
echo '[INFO] Running CucumberJS...'

#cucumberjs --tags @participant --format=json > ${jsonoutputfile}
#cucumberjs --tags @event --format=json       > ${jsonoutputfile}
#cucumberjs --tags @user --format=json        > ${jsonoutputfile}
#cucumberjs --tags @fidget --format=json      > ${jsonoutputfile}
cucumberjs --format=json                      > ${jsonoutputfile}

# Retain cucumber's exit code for testrailuploader
CUCUMBEREXITCODE=$?

echo '[INFO] CucumberJS Finished...'

# Convert json to junit xml for maven
echo '[INFO] Converting JSON to Junit XML...'
cat ${jsonoutputfile} | ./../node_modules/cucumber-junit/bin/cucumber-junit > ${xmloutputfile}

# Run maven to generate Allure report
./maven.sh

# Archive maven report in Dropbox under <dropbox>/AllureReports
# ./archiveresults.sh

# Upload results to TestRail
./testrailupload.py ${CUCUMBEREXITCODE}

# Clean up files
rm ${jsonoutputfile}
rm ${xmloutputfile}

echo '[INFO] entrypoint.sh finished...'
