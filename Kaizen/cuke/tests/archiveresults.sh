#!/bin/bash

echo '[INFO] archiveresults.sh started...'

# Vars
tarballfilename="allure-maven-$(date +%s).tar.gz"

# Tar maven output
cd site/testResults/allure-report
tar -czvf ${tarballfilename} allure-maven-plugin/

# Use dropbox uploader to put it in the correct place
dropbox_uploader.sh asdf asdf...

echo '4. Get the url to the results after they are uploaded'
# curl?
# dropbox_uploader return the new item's public url?

echo '5. Include said URL in the test results in TestRail'