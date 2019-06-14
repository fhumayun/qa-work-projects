*** Settings ***
# Libraries #
Library   ExtendedSelenium2Library


*** Variables ***
${USER}              mack@ee.io  
${PASS}              Password1@
${USERNAME_FIELD}    id=login-page__user-email
${PASSWORD_FIELD}    id=login-page__user-password
${LOGIN_BUTTON}      id=login-page__login-button
${DASHBOARD_MAP}     xpath=(//div)
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
    Page Should Contain Element     ${DASHBOARD_MAP}