#!/bin/bash

echo '[INFO] Running maven.sh...'

if [ -d "site/testResults" ]
then
    rm -rf site/testResults;
fi

# Run maven
mvn install allure:report

echo 'Cleaning up ...'

if [ ! -d "site/testResults" ]
then
    echo '[ERROR] Dir site/testResults was not created. Exiting...'
    exit 1
fi

cd site/testResults

mv allure-reports/ allure-report/
cp ../../img/thumbs_up.jpg allure-report/allure-maven-plugin/img/tests_passed.jpg
# allure report open
