*** Settings ***
Documentation  1.6 UI Changes
Resource  /Development/strax-qa/strax-rf-automation/Resources/Common.robot
Resource  /Development/strax-qa/strax-rf-automation/Resources/1.6_STRAX_Workspace.robot
Library        AllureReportLibrary
Test Setup     Begin Web Test
Test Teardown  End Web Test

*** Variables ***
${BROWSER} =  gc
${ENV} =   https://staging.strax.co  
${USER} =  Robot-c@sig.io  
${PASS} =  Password1@
${EVENTNUMBER} =  21
${EVENTNAME} =    RF-Automation

*** Test Cases ***
User should be able to create a event
    [Documentation]  Successful Event Creation
     [Tags]  Smoke
    Given a STRAX user is logged in
      And The user navigate to the events page
     When The user clicks on add new event button to create a new event
      And Enters valid values for the required fields
     Then A new event will be created

As a STRAX user I want to be able to access the SAC without issue
    [Documentation]  User is able to access SAC
     [Tags]  Smoke  
     Given a STRAX user logs into the SRM
      When the user launches an event
      Then the SAC page will load without error

As a user i want to replace ESRI maps with Google Maps so that we can implement layers, icons and other mapping functions more efficiently
    [Documentation]  Verify Google Maps Loads
     [Tags]  Smoke  New
     Given a user successfully logs into the SRM
       And joins a event
      When the SAC Page Loads without error
      Then the user can confirm there is a google logo on the bottom of the map

As a strax user I want the incident number/event name to appear in the title bar so that I can just look at title bar and know what the incident number/event name is.
    [Documentation]  Event Name Appears in title bar
     [Tags]  Smoke  
    Given a strax user is logged into the SRM
    When the user joins a "event"
     And the SAC loads without error
    Then the title bar should have the incident number/event name displayed in it

As a STRAX user I don't want map layers to appear in the settings so that I can focus on using map layers in the new layout
    [Documentation]  Map Layers no longer in Settings Menu
     [Tags]   1  SMOKE  SAC
    Given a STRAX user successfully enters the SAC
     When the "settings" menu is accessed
     Then there should not be a "Layers" menu option

As a user of STRAX > 1.5 I no longer want to edit users from the configuration menu so that I can edit these from the new header menu instead
    [Documentation]  Edit Users no Longer in Settings Menu
     [Tags]  2  SAC
     Given a STRAX user successfully accesses the SAC
     When the <Settings> menu is selected from the header bar
     Then the "Edit Users" menu tab is no longer visible

User should be able to end a event 
    [Documentation]  Successfully End a Event
     [Tags]  Smoke  SAC  
     Given The STRAX user is logged in
      When User clicks on the active event "<incidentName>" link to join
      Then User should be able to join the event successfully
       And User should be able to end an active event "<incidentName>" successfully

As a user I want to be able to find the Logout function easier so I can save time.
    [Documentation]  Logout Button in Sidebar
     [Tags]  4  SRM  SMOKE  STX-1897
    Given a STRAX user successfully logs into the SRM
     When the sidebar is fully opened
     Then the logout menu item appears at the very bottom
      And the menu icon allows the user to Logout

As a user entering call signs through STRAX, i want to convert lowercase letters to Uppercase lettering to be consistent.
    [Documentation]  Call Sign lowercase conversion
     [Tags]  SRM  SMOKE  STX-2007
    Given a user with the correct permissions is creating a "new callsign"
     When the user enters a "callsign name" with <caps>
     Then the "callsign name" will be converted to <lowercase>



#FUTURE TEST CASES (ADD THESE IN THE FUTURE)
#
#
##User can create and join event
#    [Documentation]  Successfuly Create & Join a event
#     [Tags]  Smoke
#    Given a user with the correct permisions is trying to "create an event"
#     When the user selects "Save & Enter"
#     Then the user will "create the event" and will be taken directly into the "SAC"

#STRAX saves last setting selected
#    [Documentation]  STRAX saved last selected setting
#    [Tags]  Smoke
#Save & Enter:
#    Given a user with the correct permissions last created an event using the "save & enter" <button>
#     When the user creates a new event 
#     Then the user will be shown the "save & enter" <button>
#Save:    
#    Given a user with the correct permissions last created an event using "save"
#     When the user creates a new event
#     Then the user will be shown the "save" <button>
#As a user with the permissions to see the Customer's Account information, I want a left menu item for account info that is independent of the Account icon (the current head that sits above the hamburger) so I can find things easier
#    [Documentation]  Create new left menu item: Account Settings
#     [Tags]  3  SRM  SMOKE
#    Given a STRAX user successfully signs into the SRM
#     When the user selects the "Users Account" menu option
#     Then "Account" is not a visible submenu option
#      And "Account Settings" is located at the bottom on the Main menu
#      And <Clicking> on "Account Settings" will open a modal window 
#      And the "Account Settings" modal will contain contents of the current "Account"