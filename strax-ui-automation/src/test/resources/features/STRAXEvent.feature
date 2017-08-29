Feature: STRAX Event functionality
@C44553 @SRM @Event
Scenario Outline: Verify user have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should have access to create a new event

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|
	|z-controller@ee.io|Password1@|
	|z-user@ee.io|Password1@|
@C44554	@SRM @Event
Scenario Outline: Verify user does not have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should not have access to create a new event

Examples:
	|username     |password|
	|z-viewer@ee.io|Password1@|
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
	|Latitude|<latitude>|
	|Longitude|<longitude>|
	|Description|<description>|
Then A new event with name "<incident>" should get created successfully
Examples:
	|username     |password|incident|missionType|stream|address|latitude|longitude|description|participants|
	|z-autobot@ee.io|Password1@|AutomationTestIncident1|Search & Rescue|None|1001 Broken Sound Parkway NW Suite C 33487 Boca Raton FLORIDA|||Event Created By Automation Framework|Z-User Z-Controller, Z-User Z-Viewer,Z-EEiUser Z-User|
	
@C44556 @SRM @Event
Scenario Outline: Verify user can not create a new event while assigned to another
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on add new event button to create a new event
Then can not create new event error message should be displayed 

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|
	|z-controller@ee.io|Password1@|
	|z-user@ee.io|Password1@|
@C44557 @SRM @Event
Scenario Outline: Verify only invited user can join the event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User should be able to join the event successfully
Examples:
	|username     |password|incidentName|
	|z-autobot@ee.io|Password1@|AutomationTestIncident1|
	|z-controller@ee.io|Password1@|AutomationTestIncident1|
	|z-user@ee.io|Password1@|AutomationTestIncident1|
	|z-viewer@ee.io|Password1@|AutomationTestIncident1|
@C44558 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User joins the event successfully
And User should be able to end an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|z-autobot@ee.io|Password1@|AutomationTestIncident1|
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
	|Latitude|<latitude>|
	|Longitude|<longitude>|
	|Description|<description>|
Then A new event with name "<incident>" should get created successfully
Examples:
	|username     |password|incident|missionType|stream|address|latitude|longitude|description|participants|
	|z-controller@ee.io|Password1@|AutomationTestIncident2|Search & Rescue|None|1001 Broken Sound Parkway NW, Suite C, 33487 Boca Raton,FLORIDA|||Event Created By Automation Framework|Z-User Z-Viewer,Z-EEiUser Z-User|
@C44559 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
And User joins the event successfully
Then User should be able to soft delete an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|z-autobot@ee.io|Password1@|AutomationTestIncident2|
@C44560 @SRM @Playback @Event
Scenario Outline: Verify user can playback any historic event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event history tab
And User search and clicks on the event "<incidentName>" link to playback
Then User should be able to playback "<incidentName>" event
Examples:
	|username     |password|incidentName|
	|z-autobot@ee.io|Password1@|AutomationTestIncident2|
@C44603 @SRM  @EventPlan
Scenario Outline: Verify user can create a new event plan
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event plan tab
And User clicks on add new event plan button to create a new event plan
And Enters valid values for the following fields
	|Incident|<incident>|
	|MissionType|<missionType>|
	|Stream|<stream>|
	|Address|<address>|
	|Latitude|<latitude>|
	|Longitude|<longitude>|
	|Description|<description>|
Then A new event plan with name "<incident>" should get created successfully
Examples:
	|username     |password|incident|missionType|address|latitude|longitude|description|
	|z-controller@ee.io|Password1@|AutomationTestEventPlan1|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
	|z-autobot@ee.io|Password1@|AutomationTestEventPlan2|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
	|z-user@ee.io|Password1@|AutomationTestEventPlan3|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
@arc @SRM  @EventPlan 
Scenario Outline: Verify user can archive the event plan
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event plan tab
And User clicks on archive button to archive "<eventPlan>" event plan
Then event plan "<eventPlan>" should get deleted successfully	
Examples:
	|username     |password|eventPlan|
	|z-controller@ee.io|Password1@|AutomationTestEventPlan1|
	|z-autobot@ee.io|Password1@|AutomationTestEventPlan2|
	|z-user@ee.io|Password1@|AutomationTestEventPlan3|
