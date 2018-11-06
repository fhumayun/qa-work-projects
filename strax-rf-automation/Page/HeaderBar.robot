*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***
${STRAX_LOGO} =      css=//md-icon[@class="strax__logo"]
${EVENT_INFO} =      css=//md-icon[@class="ng-scope material-icons"]
${DEFAULT_LAYOUT} =  css=//md-icon[@class="ng-binding ng-scope material-icons"]
${USERS} =           xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[2]
${CHAT_TOGGLE} =     xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[3]
${SCRIBE_TOGGLE} =   xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[4]
${EVENT_LOG} =       xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[5]
${EVENT_SETTINGS} =  xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[6]
${LEAVE_EVENT} =     xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[7]
${CONFIRM_LEAVE} =   xpath=//button[@id="confirm-dialog__button--save"]
${NOTIF_BELL} =      xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[8]
#* * * * * * * * * * * * *
#* Event Settings Modal  * 
#* * * * * * * * * * * * * 
${END_EVENT_TAB} =      xpath=//*[@id="tab-item-36"]
${CONFIRM_END_EVENT} =  xpath=//*[@id="end-event__button"]
#* * * * * * * * * *
#* REMOVED OBJECTS *
#* * * * * * * * * *
${OLD_LAYERS_TAB} =      id=tab-item-50
${OLD_EDIT_USERS_TAB} =  xpath=//*[@id="tab-item-69"]

#${LEAVE_EVENT_BUTTON} =  css=body > div:nth-child(2) > strax-header > div > md-toolbar > div > md-list:nth-child(10) > button

*** Keywords *** 
#* * * * * * * * * *
#*  SAC Header Bar *
#* * * * * * * * * *
VerifyHeaderBarLoaded
    Page Should Contain Button  ${LEAVE_EVENT}



ClickEventSettings
    Set Browser Implicit Wait  5.0
    Click Element  ${EVENT_SETTINGS}

 #  * * * * * * * * * * * * *
 #  * Event Settings Modal  * 
 #  * * * * * * * * * * * * * 
VerifyNoLayersTab
    Element Should Not Be Visible  ${OLD_LAYERS_TAB}

VerifyNoEditUsersTab
    Element Should Not Be Visible  ${OLD_EDIT_USERS_TAB}

EndEvent
    Set Focus To Element  ${END_EVENT_TAB}
    Click Button  ${END_EVENT_TAB}    
    Click Button  ${CONFIRM_END_EVENT}  




#* * * * * * * * * *
#*  SRM Header Bar *
#* * * * * * * * * *










#* * * * * * * * * *
#*    Universal    *
#* * * * * * * * * *