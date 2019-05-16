*** Settings ***
Library   String
Library   ExtendedSelenium2Library
*** Keywords ***
Begin Web Test
    Open Browser  about:blank  ${BROWSER}
    Set Selenium Speed  0.25
    LoginPage.Navigate
    LoginPage.Input
    LoginPage.Click 

End Web Test
    Close Browser 