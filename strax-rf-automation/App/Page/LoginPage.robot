*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***
${USERNAME_FIELD} =  id=login-page__user-email
${PASSWORD_FIELD} =  id=login-page__user-password
${LOGIN_BUTTON} =  id=login-page__login-button

*** Keywords ***
Navigate
    Go To  ${ENV}
    Wait Until Page Contains  Forgot your password?

Input
    Input Text  ${USERNAME_FIELD}  ${USER}
    Input Text  ${PASSWORD_FIELD}  ${PASS}

Click 
    Click Button  ${LOGIN_BUTTON}

Verify
    Set Browser Implicit Wait  5.0
    Page Should Contain Element  xpath=//md-icon[@class="ng-binding ng-scope material-icons"]   #Notification Bell
