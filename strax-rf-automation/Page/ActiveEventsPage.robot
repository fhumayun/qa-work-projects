*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***
${CREATE_EVENT_BUTTON} =  id=event-list__add-button
${EVENT} =  id=event-list__event-link-0
*** Keywords ***
ClickCreateEvent
    Set Browser Implicit Wait  5.0
#    Set Focus To Element  xpath=//*[@id="tab-content-154"]/div/div
    Click Button  ${CREATE_EVENT_BUTTON}

VerifyPageLoad
    Page Should Contain Button  ${CREATE_EVENT_BUTTON}
    
JoinEvent
    Click Element  ${EVENT}   