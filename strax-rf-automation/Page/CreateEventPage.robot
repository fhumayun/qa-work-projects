*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary



*** Variables ***
${EVENT_NAME_FIELD} =         xpath=//input[@name="incident"]
${PRE_PLAN_FIELD} =           id=event-add__prePlan
${QA_PRE_PLAN} =              id=event-add__prePlan-option-0
${SAVE_ENTER_EVENT_BUTTON} =  xpath=//button[@class="main-button md-raised md-primary md-button md-ink-ripple"]
${SAVE_EVENT_BUTTON} =        css=#event-add__save-button > button.main-button.md-raised.md-primary.md-button.md-ink-ripple
${SAVE_CARROT} =              css=#event-add__save-button > button.caret-button.md-raised.md-primary.md-button.md-ink-ripple    
*** Keywords ***
InputIncidentName
    Input Text  ${EVENT_NAME_FIELD}  ${EVENTNAME} ${EVENTNUMBER}
SelectEventPlan
    Click Element  ${PRE_PLAN_FIELD}
    Wait Until Element Is Visible  ${QA_PRE_PLAN}
    Click Element  ${QA_PRE_PLAN}
    Set Browser Implicit Wait  5.0

ClickSave&Enter
    Click Element  ${SAVE_ENTER_EVENT_BUTTON}
    Set Browser Implicit Wait  5.0 

ClickSave
    Click Element  ${SAVE_EVENT_BUTTON}
    Set Browser Implicit Wait  5.0 

ClickCarrot
    Click Element  ${SAVE_CARROT}
    Set Browser Implicit Wait  5.0 
    
EventTest
    Input Text  ${EVENT_NAME_FIELD}  ${EVENTNAME}
