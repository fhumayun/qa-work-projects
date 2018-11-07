*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
Resource  /Development/strax-qa/strax-rf-automation/Page/LoginPage.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/Events.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/SideBar.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/HeaderBar.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/GoldenLayout.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/UserAccountPopOut.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/AccountSettingsModal.robot
Resource  /Development/strax-qa/strax-rf-automation/Page/UsersPage.robot

*** Keywords ***
### Test Cases ###
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                    
#* User should be able to create a event * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                             
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *   
a STRAX user is logged in
    LoginPage.Verify 
The user navigate to the events page
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
#    SideBar.VerifyPageLoad
The user clicks on add new event button to create a new event
    Events.ClickCreateEvent
Enters valid values for the required fields  
    Events.InputIncidentName
    Events.SelectEventPlan
    Events.ClickSave&Enter
A new event will be created
#    HeaderBar.VerifyHeaderBarLoaded
    GoldenLayout.VerifyGoldenLayoutLoaded
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* User can create and join event* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                               
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
#a user with the correct permisions is trying to "create an event"
#    LoginPage.Navigate
#    LoginPage.Input
#    LoginPage.Click  
#    LoginPage.Verify
#    SideBar.GotoEventsPage
#    Events.ClickCreateEvent
#    Events.InputIncidentName
#    Events.SelectEventPlan 
#the user selects "Save & Enter"
#    Events.ClickCarrot
#    Events.ClickSave&Enter
#the user will "create the event" and will be taken directly into the "SAC"
#    HeaderBar.VerifyHeaderBarLoaded
#    GoldenLayout.VerifyGoldenLayoutLoaded
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                    
#* STRAX saves last setting selected * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                            
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
#a user with the correct permissions last created an event using the "save & enter" <button>
#the user creates a new event 
#the user will be shown the "save & enter" <button>

#a user with the correct permissions last created an event using "save"
#the user creates a new event
#the user will be shown the "save" <button>
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                    
#* As a STRAX user I don't want map layers to appear in the settings so that I can focus on using map layers in the new layout * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                             
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
a STRAX user successfully enters the SAC 
    LoginPage.Verify
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
the "settings" menu is accessed
    HeaderBar.ClickEventSettings 
there should not be a "Layers" menu option
    Headerbar.VerifyNoLayersTab
#
#    
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                 
#* As a user of STRAX > 1.5 I no longer want to edit users from the configuration menu so that I can edit these from the new header menu instead * * * * * * * * * * * * * * * * * * * * * * * *       
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
a STRAX user successfully accesses the SAC
#    LoginPage.Navigate
#    LoginPage.Input
#    LoginPage.Click  
    LoginPage.Verify
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
#    Events.ClickCreateEvent
#    Events.SelectEventPlan    
#    Events.InputIncidentName
#    Events.ClickSave&Enter
the <Settings> menu is selected from the header bar
    HeaderBar.ClickEventSettings
the "Edit Users" menu tab is no longer visible
    HeaderBar.VerifyNoEditUsersTab
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* User should be able to end a event* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                             
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *     
Given The STRAX user is logged in
    LoginPage.Verify
When User clicks on the active event "<incidentName>" link to join
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.JoinEvent
Then User should be able to join the event successfully
#    HeaderBar.VerifyHeaderBarLoaded
    GoldenLayout.VerifyGoldenLayoutLoaded
And User should be able to end an active event "<incidentName>" successfully
    HeaderBar.ClickEventSettings
    HeaderBar.EndEvent    
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* As a user I want to be able to find the Logout function easier so I can save time * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                            
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
a STRAX user successfully logs into the SRM
    LoginPage.Verify      
the sidebar is fully opened
    SideBar.OpenSideBar 
the logout menu item appears at the very bottom
    SideBar.VerifyLogOutVisible
the menu icon allows the user to Logout
    SideBar.ClickLogOut
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* As a user entering call signs through STRAX, i want to convert lowercase letters to Uppercase lettering to be consistent. * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                     
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
Given a user with the correct permissions is creating a "new callsign"
    LoginPage.Verify
    SideBar.OpenSideBar
    SideBar.ClickUsersIcon
    UsersPage.VerifyPageLoad
    UsersPage.AddCallsign
When the user enters a "callsign name" with <lowercase>
    UsersPage.CallSignName
Then the "callsign name" will be converted to <Caps>
    UsersPage.CallSignCreated