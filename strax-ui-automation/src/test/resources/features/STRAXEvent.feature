Feature: STRAX Event functionality
@C44553 @SRM @Event
Scenario Outline: Verify user have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should have access to create a new event

Examples:
	|username     |password|
	|autobot@ee.io|Password1@|
	|controller@ee.io|Password1@|
	|user@ee.io|Password1@|
@C44554	@SRM @Event
Scenario Outline: Verify user does not have access to create a new event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should not have access to create a new event

Examples:
	|username     |password|
	|viewer@ee.io|Password1@|
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
	|autobot@ee.io|Password1@|AutomationTestIncident1|Search & Rescue|None|1001 Broken Sound Parkway NW Suite C 33487 Boca Raton FLORIDA|||Event Created By Automation Framework|User Controller, User Viewer,EEiUser User|
	
@C44556 @SRM @Event
Scenario Outline: Verify user can not create a new event while assigned to another
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on add new event button to create a new event
Then can not create new event error message should be displayed 

Examples:
	|username     |password|
	|autobot@ee.io|Password1@|
	|controller@ee.io|Password1@|
	|user@ee.io|Password1@|
@C44557 @SRM @Event
Scenario Outline: Verify only invited user can join the event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User should be able to join the event successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|Password1@|AutomationTestIncident1|
	|controller@ee.io|Password1@|AutomationTestIncident1|
	|user@ee.io|Password1@|AutomationTestIncident1|
	|viewer@ee.io|Password1@|AutomationTestIncident1|
@C44558 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
Then User joins the event successfully
And User should be able to end an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|Password1@|AutomationTestIncident1|
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
	|controller@ee.io|Password1@|AutomationTestIncident2|Search & Rescue|None|1001 Broken Sound Parkway NW, Suite C, 33487 Boca Raton,FLORIDA|||Event Created By Automation Framework|User Viewer,EEiUser User|
@C44559 @SRM @Event
Scenario Outline: Verify user can end the event successfully
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on the active event "<incidentName>" link to join
And User joins the event successfully
Then User should be able to soft delete an active event "<incidentName>" successfully
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|Password1@|AutomationTestIncident2|
@C44560 @SRM @Playback @Event
Scenario Outline: Verify user can playback any historic event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event history tab
And User search and clicks on the event "<incidentName>" link to playback
Then User should be able to playback "<incidentName>" event
Examples:
	|username     |password|incidentName|
	|autobot@ee.io|Password1@|AutomationTestIncident2|
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
	|controller@ee.io|Password1@|AutomationTestEventPlan1|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
	|autobot@ee.io|Password1@|AutomationTestEventPlan2|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
	|user@ee.io|Password1@|AutomationTestEventPlan3|Search & Rescue|1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA|||Event Created By Automation Framework|
@C44624 @SRM  @EventPlan @ignore
Scenario Outline: Verify user can lock an event plan
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event plan tab
And User clicks on lock button to lock "<evenyPlan>" an event plan
Then Event plan "<evenyPlan>" should get locked	
Examples:
	|username     |password|evenyPlan|
	|controller@ee.io|Password1@|AutomationTestEventPlan1|
	|autobot@ee.io|Password1@|AutomationTestEventPlan2|
	|user@ee.io|Password1@|AutomationTestEventPlan3|
@C44625 @SRM  @EventPlan @ignore
Scenario Outline: Verify user can not edit the locked event plan
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event plan tab
And User clicks on edit button to edit "<evenyPlan>" event plan
Then event plan "<evenyPlan>" is locked error message should be disaplyed	
Examples:
	|username     |password|evenyPlan|
	|controller@ee.io|Password1@|AutomationTestEventPlan1|
	|autobot@ee.io|Password1@|AutomationTestEventPlan2|
	|user@ee.io|Password1@|AutomationTestEventPlan3|
@C44626 @SRM  @EventPlan @ignore
Scenario Outline: Verify user can unlock an event plan
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Event plan tab
And User clicks on unlock button to unlock "<evenyPlan>" an event plan
Then Event plan "<evenyPlan>" should get unlocked	
Examples:
	|username     |password|evenyPlan|
	|controller@ee.io|Password1@|AutomationTestEventPlan1|
	|autobot@ee.io|Password1@|AutomationTestEventPlan2|
	|user@ee.io|Password1@|AutomationTestEventPlan3|