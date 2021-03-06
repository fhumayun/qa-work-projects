*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/AccountSettings.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/Events.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/GoldenLayout.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/HeaderBar.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/LoginPage.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/SideBar.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/UserAccountSettings.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/Users.robot
Resource  /Users/Mack/Development/strax-qa/strax-rf-automation/App/Page/VideoFeeds.robot

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
The user clicks on add new event button to create a new event
    Events.ClickCreateEvent
Enters valid values for the required fields  
    Events.InputIncidentName
    Events.SelectEventPlan
    Events.ClickSave&Enter
A new event will be created
    GoldenLayout.VerifyGoldenLayoutLoaded
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* As a STRAX user I want to be able to access the SAC without issue * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                  
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
a STRAX user logs into the SRM
    LoginPage.Verify 
the user launches an event
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
the SAC page will load without error
    GoldenLayout.VerifyGoldenLayoutLoaded
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* As a user i want to replace ESRI maps with Google Maps so that we can implement layers, icons and other mapping functions more efficiently  * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                        
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
a user successfully logs into the SRM
    LoginPage.Verify
joins a event
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
the SAC Page Loads without error
    GoldenLayout.VerifyGoldenLayoutLoaded
the user can confirm there is a google logo on the bottom of the map
    GoldenLayout.GoogleLogoLoaded
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                     
#* As a strax user I want the incident number/event name to appear in the title bar so that I can just look at title bar and know what the incident number/event name is.* * * * * * * * * * * * 
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
a strax user is logged into the SRM
    LoginPage.Verify 
the user joins a "event"
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
the SAC loads without error
    GoldenLayout.VerifyGoldenLayoutLoaded
the title bar should have the incident number/event name displayed in it
    HeaderBar.VerifyEventNameShown
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
    LoginPage.Verify
    SideBar.OpenSideBar
    SideBar.GotoEventsPage
    Events.VerifyPageLoad
    Events.JoinEvent
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


#FUTURE TEST CASES (ADD THESE IN THE FUTURE)
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