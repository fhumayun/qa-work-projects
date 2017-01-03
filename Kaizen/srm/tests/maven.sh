#!/bin/bash

echo '[INFO] Running maven.sh...'

if [ -d "site/testResults" ]
then
    echo "[INFO] dir site/testResults already exists. Removing..."
    rm -rf site/testResults;
fi

# Run maven
mvn install allure:report

echo "[INFO] Cleaning up ..."

if [ ! -d "site/testResults" ]
then
    echo "[ERROR] Dir site/testResults was not created. Exiting..."
    exit 1
fi

cd site/testResults

# Rename the report dir
mv allure-reports/ allure-report/

# Replace the stock Allure image with a custom thumbs-up photo
cp ../../img/thumbs_up.jpg allure-report/allure-maven-plugin/img/tests_passed.jpg
# allure report open