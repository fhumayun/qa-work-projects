Feature: STRAX Event functionality
@C44553 @SRM @Event
Scenario Outline: Verify user have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should have access to create a new event

Examples:
	|username     |password|
	|autobot@ee.io|eei|
	|controller@ee.io|eei|
	|user@ee.io|eei|
@C44554	@SRM @Event
Scenario Outline: Verify user does not have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should not have access to create a new event

Examples:
	|username     |password|
	|viewer@ee.io|eei|
@C44555 @SRM @Event
Scenario Outline: Verify user can create a new event 
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on add new event button to create a new event
And Enters valid values for the following fields and "<participants>"
	|Incident|<incident>|
	|MissionType|<missionType>|
	|Stream|<stream>|
	|Address|<address>|
	|AptSuiteUnit|<aptSuiteUnit>|
	|ZipCode|<zipcode>|
	|City|<city>|
	|State|<state>|
	|Latitude|<latitude>|
	|Longitude|<longitude>|
	|Description|<description>|
Then A new event with name "<incident>" should get created successfully
Examples:
	|username     |password|incident|missionType|stream|address|aptSuiteUnit|zipcode|city|state|latitude|longitude|description|participants|
	|autobot@ee.io|eei|AutomationTestIncident1|Search & Rescue|red.stream|1001 Broken Sound Parkway NW|Suite C|33487|Boca Raton|FLORIDA|||Event Created By Automation Framework|User Controller, User Viewer,EEiUser User|
	
@C44556 @SRM @Event
Scenario Outline: Verify user can not create a new event while assigned to another
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on add new event button to create a new event
Then can not create new event error message should be displayed 

Examples:
	|username     |password|
	|autobot@ee.io|eei|
	|controller@ee.io|eei|
	|user@ee.io|eei|
@C44557 @SRM @Event
Scenario Outline: Verify only invited user can join the event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User should be able to join the event successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|eei|AutomationTestIncident1|
	|controller@ee.io|eei|AutomationTestIncident1|
	|user@ee.io|eei|AutomationTestIncident1|
	|viewer@ee.io|eei|AutomationTestIncident1|
@C44558 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User joins the event successfully
And User should be able to end an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|eei|AutomationTestIncident1|
@C44555 @SRM @Event 
Scenario Outline: Verify user can create a new event 
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on add new event button to create a new event
And Enters valid values for the following fields and "<participants>"
	|Incident|<incident>|
	|MissionType|<missionType>|
	|Stream|<stream>|
	|Address|<address>|
	|AptSuiteUnit|<aptSuiteUnit>|
	|ZipCode|<zipcode>|
	|City|<city>|
	|State|<state>|
	|Latitude|<latitude>|
	|Longitude|<longitude>|
	|Description|<description>|
Then A new event with name "<incident>" should get created successfully
Examples:
	|username     |password|incident|missionType|stream|address|aptSuiteUnit|zipcode|city|state|latitude|longitude|description|participants|
	|controller@ee.io|eei|AutomationTestIncident2|Search & Rescue|red.stream|1001 Broken Sound Parkway NW|Suite C|33487|Boca Raton|FLORIDA|||Event Created By Automation Framework|User Viewer,EEiUser User|
@C44559 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
And User joins the event successfully
Then User should be able to soft delete an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|eei|AutomationTestIncident2|
@C44559 @SRM @Playback @Event
Scenario Outline: Verify user can playback any historic event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event history tab
And User search and clicks on the event "<incidentName>" link to playback
Then User should be able to playback "<incidentName>" event
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|eei|AutomationTestIncident2|


