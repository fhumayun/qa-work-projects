#!/bin/bash

echo '[INFO] archiveresults.sh started...'

# Vars
tarballfilename="allure-maven-$(date +%s).tar.gz"

# Tar maven output
cd site/testResults/allure-report || exit
tar -czvf "${tarballfilename}" allure-maven-plugin/

# Use dropbox uploader to put it in the correct place
dropbox_uploader.sh "${tarballfilename}" "AllureReports/"