*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***
${GOOGLE_MAPS} =  id=tabbedLayout
#//*[@id="tabbedLayout"]/div/div/div/div/div/div[2]/div[3]/div/strax-map-view/div/strax-map-menu

*** Keywords ***
VerifyGoldenLayoutLoaded
    Page Should Contain Element  ${GOOGLE_MAPS}

