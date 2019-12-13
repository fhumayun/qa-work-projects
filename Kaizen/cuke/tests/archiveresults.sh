#!/bin/bash
set +x

echo '[INFO] archiveresults.sh started...'

# Vars
EPOCH=$(date +%s)
FILENAME="allure-maven-${EPOCH}.tar.gz"

# Tar maven output
cd site/testResults/allure-report || exit

tar -czvf "${FILENAME}" allure-maven-plugin/

# Use dropbox uploader to put it in the correct place
dropbox_uploader.sh upload "${FILENAME}" AllureReports/
