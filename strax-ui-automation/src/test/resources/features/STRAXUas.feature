Feature: STRAX UASs functionality
@C44551 @UAS 
Scenario Outline: Verify user have access to create a new UAS
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to UASs menu
Then User should have access to create a new UAS

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|
@fixedcam @UAS 
Scenario Outline: Verify user can access fixed camera feed in active event
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to UASs menu
And user enters valid values for following UAS fields
	  | Name    | <name>    |
      | Feed  | <feed>  |
      | Make | <make> |
      | SerialNumber      | <serialnumber>      |
      | Model     | <model>     |
      | Status    | <status>    |
      | Type   | <type>   |

Then UAS should get added in the list "<name>"
When User navigates to Events page
And User clicks on add new event button to create a new event
And Enters valid values for the following fields and "<participants>"
      | Incident    | <incident>    |
      | CaseNumber  | <casenumber>  |
      | MissionType | <missionType> |
      | Stream      | <stream>      |
      | Address     | <address>     |
      | Latitude    | <latitude>    |
      | Longitude   | <longitude>   |
      | Description | <description> |
Then A new event with name "<incident>" should get created successfully

Examples:
|name     |feed              	|make         			|serialnumber  |model                  |status      |type         |username           | password   | incident                | casenumber                   | missionType     | stream | address                                                         | latitude | longitude | description                           | participants                     |
|qatest11 |Front-Office-Camera	|Lockheed Martin        |qa007         |Indago       			|Available   |Multi-copter|z-autobot@ee.io | Password1@ | AutomationTestIncident121 | AutomationTest_CaseNumber002 | Search & Rescue | None   | 1001 Broken Sound Parkway NW, Suite C, 33487 Boca Raton,FLORIDA |          |           | Event Created By Automation Framework | Z-User Z-Viewer,Z-EEiUser Z-User |


	  