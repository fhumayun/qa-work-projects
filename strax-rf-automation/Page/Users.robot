*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
*** Variables ***
${SUBUNITS} =              xpath=//md-tab-item[@id="tab-item-14058"]
${CREATE_SUBUNIT} =        xpath=//md-icon[@id="md-blue"]
${CALLSIGN} =              xpath=//md-tab-item[@id="tab-item-15223"]
${CREATE_CALLSIGN} =       xpath=//md-icon[@id="md-blue"]
${CALLSIGN_NAME_FIELD} =   xpath=//input[@name="callSignName"]
${CALLSIGN_NUMBER} =  Generate Random String  4  [NUMBERS]
${SAVE_CALLSIGN} =         css=//button[@id="units-managment__input--save"]
${CALLSIGN_SUCCESS} =      xpath=//span[@class="md-toast-text ng-binding"]
${UserPageLoaded} =        id=loginId 
*** Keywords ***
######################
###     Common     ###
######################


######################
###      USERS     ###
######################
VerifyPageLoad
    Element Should Contain  ${UserPageLoaded}  User Id

######################
###    CallSigns   ###
######################
GoToCallsigns
    Click Element  ${CALLSIGN_TAB}
AddCallsign
    Click Element   ${CREATE_CALLSIGN} 

CallSignName
    Input Text  ${CALLSIGN_NAME_FIELD}  QA${CALLSIGN_NUMBER}

SaveCallSign
    Click Element  ${SAVE_CALLSIGN}
CallSignCreated
    Element Should Be Visible  ${CALLSIGN_SUCCESS}       
######################
###    SubUnits    ###
######################

GoToSubunits
    Click Element  ${SUBUNITS} 

AddSubunit
    Click Element  ${CREATE_SUBUNIT}   
  