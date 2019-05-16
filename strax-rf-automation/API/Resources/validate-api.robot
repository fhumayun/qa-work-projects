*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/API/Page/loginPage.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/API/Page/SideBar.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/API/Page/Users.robot

*** Keywords ***
### Test Cases ###
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                    
#* Verify Successful Post * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                             
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *   

Given a successful POST request
    loginPage.Navigate
    loginPage.Input
    loginPage.Click 
    loginPage.Verify
When Testbot opens strax
    SideBar.OpenSideBar
    SideBar.ClickUsersIcon
Then API-Test user will be visible
    Users.Verify