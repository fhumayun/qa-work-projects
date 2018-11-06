*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***     
${SETTINGS_MODAL} =  xpath=/html/body/div[5]/md-dialog          #OLD xpath=/html/body/div[8]/md-dialog
${CANCEL_BUTTON} =  id=account__button--cancel
${SAVE_BUTTON} =  id=account__button--save
@{MODAL_CONTENTS} =  Name  Website  Email  Phone  Language  Description  Address  Address


*** Keywords ***

VerifyModalLoads
#   Wait Until Element Is Visible  ${SETTINGS_MODAL}  5.0
    Element Should Be Visible  ${SETTINGS_MODAL}

VerifyModalContents
#    Set Focus To Element  ${SETTINGS_MODAL}
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[0]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[1]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[2]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[3]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[4]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[5]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[6]
    Element Should Contain  ${SETTINGS_MODAL}  @{MODAL_CONTENTS}[7]
    Element Should Be Visible  ${CANCEL_BUTTON}
    Element Should Be Visible  ${SAVE_BUTTON}


