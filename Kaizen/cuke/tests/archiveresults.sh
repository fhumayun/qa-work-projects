#!/bin/bash

echo '[INFO] archiveresults.sh started...'

# Vars
tarballfilename="allure-maven-$(date +%s).tar.gz"

# Tar maven output
cd site/testResults/allure-report || exit
tar -czvf "${tarballfilename}" allure-maven-plugin/

# Use dropbox uploader to put it in the correct place
dropbox_uploader.sh "${tarballfilename}" "AllureReports/"

echo '4. Get the url to the results after they are uploaded for TestRail'
# dropbox_uploader return the new item's public url?
