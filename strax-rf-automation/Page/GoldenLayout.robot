*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary

*** Variables ***
${GOOGLE_MAPS} =  id=tabbedLayout
${GOOGLE_WATERMARK} =  id=map  #xpath=//*[@id="map"]/div/div/div[2]/a/div/img
#//*[@id="tabbedLayout"]/div/div/div/div/div/div[2]/div[3]/div/strax-map-view/div/strax-map-menu

*** Keywords ***
VerifyGoldenLayoutLoaded
    Page Should Contain Element  ${GOOGLE_MAPS}

VerifyGoogleLogoLoaded
    Page Should Contain Image  ${GOOGLE_WATERMARK}

    