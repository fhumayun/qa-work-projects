*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
Resource  /Development/robot-scripts/Project-1/Page/LoginPage.robot
Resource  /Development/robot-scripts/Project-1/Page/CreateEventPage.robot
Resource  /Development/robot-scripts/Project-1/Page/SideBar.robot
Resource  /Development/robot-scripts/Project-1/Page/HeaderBar.robot
Resource  /Development/robot-scripts/Project-1/Page/GoldenLayout.robot
Resource  /Development/robot-scripts/Project-1/Page/ActiveEventsPage.robot
Resource  /Development/robot-scripts/Project-1/Page/UserAccountPopOut.robot
Resource  /Development/robot-scripts/Project-1/Page/AccountSettingsModal.robot
Resource  /Development/robot-scripts/Project-1/Page/UsersPage.robot

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
    ActiveEventsPage.ClickCreateEvent
Enters valid values for the required fields  
    CreateEventPage.InputIncidentName
    CreateEventPage.SelectEventPlan
    CreateEventPage.ClickSave&Enter
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
#    ActiveEventsPage.ClickCreateEvent
#    CreateEventPage.InputIncidentName
#    CreateEventPage.SelectEventPlan 
#the user selects "Save & Enter"
#    CreateEventPage.ClickCarrot
#    CreateEventPage.ClickSave&Enter
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
    ActiveEventsPage.VerifyPageLoad
    ActiveEventsPage.JoinEvent
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
    ActiveEventsPage.VerifyPageLoad
    ActiveEventsPage.JoinEvent
#    ActiveEventsPage.ClickCreateEvent
#    CreateEventPage.SelectEventPlan    
#    CreateEventPage.InputIncidentName
#    CreateEventPage.ClickSave&Enter
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
    ActiveEventsPage.JoinEvent
Then User should be able to join the event successfully
#    HeaderBar.VerifyHeaderBarLoaded
    GoldenLayout.VerifyGoldenLayoutLoaded
And User should be able to end an active event "<incidentName>" successfully
    HeaderBar.ClickEventSettings
    HeaderBar.EndEvent    
#
#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                                                                                                    
#* As a user with the permissions to see the Customer's Account information, I want a left menu item for account info that is independent of the Account icon (the current head that sits above the hamburger) so I can find things easier * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *                                            
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
#a STRAX user successfully signs into the SRM
#    LoginPage.Navigate
#    LoginPage.Input
#    LoginPage.Click  
#    LoginPage.Verify 
#the user selects the "Users Account" menu option
#    SideBar.OpenSideBar
#    SideBar.ClickUsersAccount
#"Account" is not a visible submenu option
#    UserAccountPopOut.AccountNotVisible
#"Account Settings" is located at the bottom on the Main menu
#    SideBar.VerifyAccountVisible
#<Clicking> on "Account Settings" will open a modal window 
#    SideBar.ClickAccountSettings
#the "Account Settings" modal will contain contents of the current "Account" 
#    AccountSettingsModal.VerifyModalLoads
#    AccountSettingsModal.VerifyModalContents
#
#
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