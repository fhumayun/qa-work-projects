*** Settings ***

Library   ExtendedSelenium2Library

*** Variables ***
${GOOGLE_MAPS} =  id=tabbedLayout
${LOGO_GOOGLE} =  xpath=//*[@id="map"]/div/div/div[2]/a/div/img
#//*[@id="tabbedLayout"]/div/div/div/div/div/div[2]/div[3]/div/strax-map-view/div/strax-map-menu

*** Keywords ***
VerifyGoldenLayoutLoaded
    Page Should Contain Element  ${GOOGLE_MAPS}

GoogleLogoLoaded
    Page Should Contain Image  ${LOGO_GOOGLE}

    