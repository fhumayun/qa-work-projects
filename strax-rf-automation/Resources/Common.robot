*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
*** Keywords ***
Begin Web Test
    Set Library Search Order  String  ExtendedSelenium2Library  SeleniumLibrary
    Open Browser  about:blank  ${BROWSER}
    Set Selenium Speed  0.25
    LoginPage.Navigate
    LoginPage.Input
    LoginPage.Click 

End Web Test
    Close Browser 
