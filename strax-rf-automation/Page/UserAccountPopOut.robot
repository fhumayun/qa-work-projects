*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
*** Variables ***
@{USER_SETTINGS_CONTENTS} =  Call Sign  Change Password
@{OLD_USER_SETTINGS_CONTENTS} =  Account
${USER_SETTINGS_POPOUT} =  xpath=//*[@id="menu_container_3"]
${BACKDROP} =  xpath=/html/body/md-backdrop
${CALL_SIGN_BUTTON} =  xpath=//md-icon[@class="ng-scope material-icons"] 
${CHANGE_PASSWORD_BUTTON} =  xpath=//button[@id="main-menu__button--change-password"]
${OLD_ACCOUNT_BUTTON} =  xpath=//a[@id="main-menu__button--open-account-dialog"]
*** Keywords ***
AccountNotVisible
    Element Should Be Visible  ${CHANGE_PASSWORD_BUTTON}
    Element Should Be Visible  ${CALL_SIGN_BUTTON}
    Element Should Not Be Visible  ${OLD_ACCOUNT_BUTTON}
    Click Element  ${BACKDROP}



#    Element Should Be VisibleContain  ${USER_SETTINGS_POPOUT}  @{USER_SETTINGS_CONTENTS}[0] 
#    Element Should Contain  ${USER_SETTINGS_POPOUT}  @{USER_SETTINGS_CONTENTS}[1]
#    Element Should Not Contain  ${USER_SETTINGS_POPOUT}  @{OLD_USER_SETTINGS_CONTENTS}[0]

#    Element Should Not Be Visible  ${ACCOUNT_ICON} 
#    Click Element  ${BACKDROP}
    
