#!/bin/bash
echo '[INFO] entrypoint.sh started...'

# Make sure req's are installed
echo "Checking node installed..."
if [[ $(which node | wc -l | xargs) < 1 ]]; then
    echo "Please install 'node' or symlink node->nodejs and try again..."
    exit 1
fi

# Deps
npm i

echo "Checking cucumber installed..."
if [[ $(npm list -g 2>/dev/null | grep cucumber | wc -l | xargs) < 1 ]]; then
    echo "Please install cucumber (npm install -g cucumber)..."
    exit 1
fi

# Go to tests dir and execute the runner.
cd tests

# Run cucumber tests
# todo Cuke needs to output results as json into jsonoutput.json for testrailupload.py to work
#./cuke.sh
#CUCUMBEREXITCODE=$?
CUCUMBEREXITCODE=1

# Upload results to TestRail
# todo Execute maven.sh first and then upload it to Dropbox or S3 and include the public link in testrailupload.py...
# Add code here to make a tarball with the maven results or Junit xml and upload somewhere
# ./maven.sh
# ./archiveresults.sh
./testrailupload.py ${CUCUMBEREXITCODE}

echo '[INFO] entrypoint.sh finished...'
