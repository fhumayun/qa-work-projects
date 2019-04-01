*** Settings ***
Library   String
Library   ExtendedSelenium2Library

*** Variables ***
${USERNAME_FIELD} =  id=login-page__user-email                                          # id=login-page__user-email     <-- 1.6 
${PASSWORD_FIELD} =  id=login-page__user-password                                       # id=login-page__user-password  <-- 1.6 
${LOGIN_BUTTON} =    id=login-page__login-button                                        # id=login-page__login-button   <-- 1.6 

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

