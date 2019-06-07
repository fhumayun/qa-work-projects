*** Settings ***
# Libraries #
Library   ExtendedSelenium2Library


# Resources #
Resource    ../Page/LoginPage.robot
Resource    ../Page/SideBar.robot
Resource    ../Page/Events.robot
Resource    ../Page/GoldenLayout.robot
*** Keywords ***
# Test Cases #
# Verify a STRAX user can Login with valid credentials 
Given a STRAX user has opened the homepage
    loginPage.Navigate
When the USER inputs valid credentials  
    loginPage.Input
    loginPage.Click 
Then the USER will be logged into STRAX
    loginPage.Verify


# User should be able to create a event
Given a STRAX user is logged in
    loginPage.Navigate
    loginPage.Input
    loginPage.Click 
    loginPage.Verify
And The user navigate to the events page
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
When The user clicks on add new event button to create a new event
    Events.ClickCreateEvent
And Enters valid values for the required fields
    Events.InputIncidentName
    Events.SelectEventPlan
    Events.ClickSave&Enter
Then A new event will be created
    GoldenLayout.VerifyGoldenLayoutLoaded


#As a STRAX user I want to be able to access the SAC without issue
Given a STRAX user logs into the SRM
    loginPage.Navigate
    loginPage.Input
    loginPage.Click 
    loginPage.Verify
When the user launches an event
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
Then the SAC page will load without error
    GoldenLayout.VerifyGoldenLayoutLoaded