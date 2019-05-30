*** Settings ***
Documentation   Front End Test Suite
...
...  Strax Version: 2.0
...
...  Included Librarys
...      - ExtendedSelenium2Library
...      - ExtendedRequestsLibrary (Used For Clearing Database Entries)
...
...  Additional Docs: /strax-rf-automation/Documentation
...
...  Run On LocalHost CMD:   robot -d ../Logs 2.0_Fe.robot
...  Run On SauceLabs CMD:   robot -v REMOTE_URL:http://mmaney:ad0b6583-5d8f-46f9-8ca8-f32fc56bc2ba@ondemand.saucelabs.com:80/wd/hub 2.0_Fe.robot

# Libraries #
Library  ExtendedSelenium2Library
Library  SauceLabs.py

# Resources #
Resource    ../Tests/2.0_Common.robot
Resource    ../Resources/2.0_Resources.robot
# Setup/Teardown #
Test Setup  Open test browser
Test Teardown  Close test browser

*** Variables ***

# Sauce Integration Variables #
${ENV}      https://demo.strax.co
${BROWSER}  google chrome
${REMOTE_URL}
${DESIRED_CAPABILITIES}

# Selenium Test Variables #



*** Test Cases ***
Verify a STRAX user can Login with valid credentials
    [Documentation]  User can login to STRAX
    [Tags]  Login  Smoke
    Given a STRAX user has opened the homepage
    When the USER inputs valid credentials
    Then the USER will be logged into STRAX


User should be able to create a event
    [Documentation]  Successful Event Creation
     [Tags]  Smoke
    Given a STRAX user is logged in
      And The user navigate to the events page
     When The user clicks on add new event button to create a new event
      And Enters valid values for the required fields
     Then A new event will be created


As a STRAX user I want to be able to access the SAC without issue
    [Documentation]  User is able to access SAC
    [Tags]  Smoke  
    Given a STRAX user logs into the SRM
    When the user launches an event
    Then the SAC page will load without error


