*** Settings ***
Resource    /Users/Mack/Development/strax-qa/strax-rf-automation/SauceLabs/common.robot

Library  ExtendedSelenium2Library
Library  SauceLabs.py

Test Setup  Open test browser
Test Teardown  Close test browser

*** Variables ***

${BROWSER}  google chrome
${REMOTE_URL}
${DESIRED_CAPABILITIES}

${STRAX_LOGO}    xpath=(//div)

*** Test Cases ***
# TO RUN TEST USE CMD
# robot -v REMOTE_URL:http://mmaney:ad0b6583-5d8f-46f9-8ca8-f32fc56bc2ba@ondemand.saucelabs.com:80/wd/hub test_saucelabs_login.robot
Incorrect username or password
    [Tags]  Login
    Go to  https://demo.strax.co

    Page should contain element  id=login-page__user-email
    Page should contain element  id=login-page__user-password

    Input Text    id=login-page__user-email        Mack@ee.io
    Input Text    id=login-page__user-password     Password1@
    
    Click Element    id=login-page__login-button
        
    #Wait Until Angular Ready    5s
    #Focus   xpath=/html/body/div[2]
    Page should contain element    ${STRAX_LOGO}

