Feature: STRAX Event functionality

  @C44553 @SRM
  Scenario Outline: Verify user have access to create a new event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then User should have access to create a new event

    Examples: 
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
      | z-controller@ee.io | Password1@ |
      | z-user@ee.io       | Password1@ |

  @C44554 @SRM
  Scenario Outline: Verify user does not have access to create a new event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then User should not have access to create a new event

    Examples: 
      | username       | password   |
      | z-viewer@ee.io | Password1@ |
    @C86939 @SRM  
   Scenario Outline: Verify user does not have access to view event history
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then User should not have access to view event history

    Examples: 
      | username       | password   |
      | z-viewer@ee.io | Password1@ |

  @C44555 @SRM @Smoke
  Scenario Outline: Verify user can create a new event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
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
      | username        | password   | incident                | casenumber                   | missionType     | stream | address                                                       | latitude | longitude | description                           | participants                                          |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident1 | AutomationTest_CaseNumber001 | Search & Rescue | None   | 1001 Broken Sound Parkway NW Suite C 33487 Boca Raton FLORIDA |          |           | Event Created By Automation Framework | Z-User Z-Controller, Z-User Z-Viewer,Z-EEiUser Z-User |

  @C44556 @SRM
  Scenario Outline: Verify user can not create a new event while assigned to another
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on add new event button to create a new event
    Then can not create new event error message should be displayed

    Examples: 
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
      | z-controller@ee.io | Password1@ |
      | z-user@ee.io       | Password1@ |

  @C44557 @SRM @Smoke
  Scenario Outline: Verify only invited user can join the event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User should be able to join the event successfully

    Examples: 
      | username           | password   | incidentName            |
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 |
      | z-controller@ee.io | Password1@ | AutomationTestIncident1 |
      | z-user@ee.io       | Password1@ | AutomationTestIncident1 |
      | z-viewer@ee.io     | Password1@ | AutomationTestIncident1 |
      
  @C86159 @SAC
  Scenario Outline: Verify title bar contains Incident Number from SRM
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then Browser title bar should have incident number/event name displayed in it "<incidentName>"

    Examples: 
      | username           | password   | incidentName            |
      | z-controller@ee.io | Password1@ | AutomationTestIncident1 |


  @C48854 @SAC
  Scenario Outline: Verify google map is loaded in the active event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then Google map should get loaded successfully

    Examples: 
      | username           | password   | incidentName            |
      | z-controller@ee.io | Password1@ | AutomationTestIncident1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 |
      | z-user@ee.io       | Password1@ | AutomationTestIncident1 |

  @C48855 @SAC
  Scenario Outline: Verify event log window loads correctly
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then Event log window loads correctly

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
      | z-controller@ee.io | Password1@ | AutomationTestIncident1 	|
      | z-user@ee.io       | Password1@ | AutomationTestIncident1   |
    @C86161 @SAC
  Scenario Outline: Verify chat window loads correctly
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then chat window loads correctly

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-user@ee.io       | Password1@ | AutomationTestIncident1   |
 @SAC @C87060     
Scenario Outline: Verify user can toggle various map layers
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User clicks on the map menu icon
	Then The list of map layers is displayed
	And The user can toggle various map layers
	
     Examples: 
      | username           	  | password   | incidentName 			   |
      | z-autobot@ee.io       | Password1@ | AutomationTestIncident1   |   
      
 @SAC @C101695    
Scenario Outline: Verify user can toggle various KLV map layers
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User navigates to map layer settings
	And The user enable "<mapLayer>" map layer
	
     Examples: 
      | username           	  | password   | incidentName 			   | mapLayer			|
      | z-autobot@ee.io       | Password1@ | AutomationTestIncident1   | Florida Airspace   | 
      @C86168 @SAC
  Scenario Outline: Verify user can add a point on map
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User adds a point on map

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
 
       @C86169 @SAC
  Scenario Outline: Verify user can draw a polygon on map
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User draw a polygon on map

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
        
        @C86170 @SAC
  Scenario Outline: Verify user can draw a polyline on map
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User draw a polyline on map

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
           @C86243 @SAC 
  Scenario Outline: Verify user can draw a POI inside polygon
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User drop a POI inside polygon

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
           @C86244 @SAC
  Scenario Outline: Verify user can draw a POI inside polyline
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User drop a POI inside polyline

    Examples: 
      | username           | password   | incidentName 			 	|
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 	|
  @C48856 @SRM @ignore
  Scenario Outline: Verify users can see video feed
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User should see video feed

    Examples: 
      | username           | password   | incidentName            |
      | z-controller@ee.io | Password1@ | AutomationTestIncident1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestIncident1 |
      | z-user@ee.io       | Password1@ | AutomationTestIncident1 |

  @C44558 @SAC @Smoke
  Scenario Outline: Verify user can end the event successfully
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User joins the event successfully
    And User should be able to end an active event "<incidentName>" successfully

    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident1 |
     
     @test1 @ignore
  Scenario Outline: Verify user can end the event successfully
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User joins the event successfully
	And the SAC application loads completely
	And the User clicks on the settings icon
    And the Settings Modal appears successfully
	When the user selects the `End Event` option
	And the Modal provides a confirmation message
	And RED END EVENT button is available to select
	And the User clicks END EVENT
    Then User should be able to end an active event successfully now
    Examples: 
      | username        | password   | incidentName            |
      | yogi@msys.com | Password1@ | QATest22 				   |

  @C44555 @SRM
  Scenario Outline: Verify user can create a new event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
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
      | username           | password   | incident                | casenumber                   | missionType     | stream | address                                                         | latitude | longitude | description                           | participants     |
      | z-controller@ee.io | Password1@ | AutomationTestIncident2 | AutomationTest_CaseNumber002 | Search & Rescue | None   | 1001 Broken Sound Parkway NW, Suite C, 33487 Boca Raton,FLORIDA |          |           | Event Created By Automation Framework | Z-EEiUser Z-User |
	
	  @C102528 @SAC
  Scenario Outline: Verify user can be added to an active event from the SAC Edit Users pane
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User joins the event successfully
    And User should be able to add the user "<participants>" from SAC edit users pane
    And User close SAC map
    And Logs out from STRAX
    When User Enters Valid "<username1>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User should be able to join the event successfully

    Examples: 
      | username        | password   | incidentName            |participants    |username1     |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |Z-User Z-Viewer |z-viewer@ee.io|
    
        @C102593 @SAC
  Scenario Outline: Verify user can be removed from an active event from the SAC Edit Users pane
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User joins the event successfully
    And User should be able to remove the user "<participants>" from SAC edit users pane
    And User close SAC map
    And Logs out from STRAX
    When User Enters Valid "<username1>" and "<password>"
    Then User should not have access to join the event "<incidentName>"

    Examples: 
      | username        | password   | incidentName            |participants    |username1     |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |Z-User Z-Viewer |z-viewer@ee.io|  
     @C102594 @SAC
  Scenario Outline: Verify user can be added to an active event from SAC User management menu
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User joins the event successfully
    And User should be able to add the user "<participants>" from SAC User management menu
    And User close SAC map
    And Logs out from STRAX
    When User Enters Valid "<username1>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User should be able to join the event successfully

    Examples: 
      | username        | password   | incidentName            |participants    |username1     |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |Z-Viewer Z-User |z-viewer@ee.io|
     
      @C102595 @SAC
  Scenario Outline: Verify user can be removed from an active event from SAC User management menu
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User joins the event successfully
    And User should be able to remove the user "<participants>" from SAC User management menu
    And User close SAC map
    And Logs out from STRAX
    When User Enters Valid "<username1>" and "<password>"
    Then User should not have access to join the event "<incidentName>"

    Examples: 
      | username        | password   | incidentName            |participants    |username1     |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |Z-Viewer Z-User |z-viewer@ee.io|
	  
  @C44559 @SRM
  Scenario Outline: Verify user can end the event successfully
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    And User joins the event successfully
    Then User should be able to soft delete an active event "<incidentName>" successfully

    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |
      
    @SRM @C86150
  Scenario Outline: Verify that the Case Number can be changed after a event has been closed
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search the event "<incidentName>" to edit case number
    Then User should be able to edit or add a "<casenumber>" to the event

    Examples: 
      | username        | password   | incidentName            | casenumber        |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |  Case_007_Updated |

  @C44560 @PlayBack
  Scenario Outline: Verify user can playback any historic event
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search and clicks on the event "<incidentName>" link to playback
    Then User should be able to playback "<incidentName>" event

    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |
  
    @Chat @PlayBack
  Scenario Outline: Verify user can relocate chat window in playback
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search and clicks on the event "<incidentName>" link to playback
    Then User should be able to relocate chat window

    Examples: 
      | username        | password   | incidentName            |
      | yogi@msys.com   | Password1@ | AB Test 7-2 2p          |
 
   @PlayBack @C102697    
Scenario Outline: Verify user can toggle various map layers in event playback
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search and clicks on the event "<incidentName>" link to playback
    And User clicks on the map menu icon
	Then The list of map layers is displayed
	And The user can toggle various map layers
	
     Examples: 
      | username           	  | password   | incidentName 			   |
      | z-autobot@ee.io       | Password1@ | AutomationTestIncident2   |   
      
    @slider @PlayBack @ignore
  Scenario Outline: Verify moving circle on slider correctly moves forward and reverse
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search and clicks on the event "<incidentName>" link to playback
    Then User should be able to playback "<incidentName>" event
    And Playback slider moves forward

    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |
      
   @follow @PlayBack @ignore
  Scenario Outline: Verify Auto-Follow UAS feature works
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
    And User search and clicks on the event "<incidentName>" link to playback
    And User clicks on Auto-Follow UAS button
    Then Auto-Follow UAS feature should work

    Examples: 
      | username        | password   | incidentName |
      | z-autobot@ee.io | Password1@ | Staging-T1 |
      
   @C86149 @PlayBack
Scenario Outline: Verify play / pause feature works correctly
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event history tab
	And User search and clicks on the event "<incidentName>" link to playback
	And User should be able to open the event in playback
	And User clicks on the play button of event playback
	Then The playback should start to play
	And The user will click Pause
	Then The event playback should Pause "<incidentName>"
    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident2 |	
	

  @C44603 @SRM @Smoke
  Scenario Outline: Verify user can create a new event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on add new event plan button to create a new event plan
    And Enters valid values for the following fields
      | EventPlan   | <eventPlan>    |
      | MissionType | <missionType> |
      | Stream      | <stream>      |
      | Address     | <address>     |
      | Latitude    | <latitude>    |
      | Longitude   | <longitude>   |
      | Description | <description> |
    Then A new event plan with name "<eventPlan>" should get created successfully

    Examples: 
      | username           | password   | eventPlan                 | missionType     | address                                                       | latitude | longitude | description                           |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan1 | Search & Rescue | 1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA |          |           | Event Created By Automation Framework |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 | Search & Rescue | 1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA |          |           | Event Created By Automation Framework |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan3 | Search & Rescue | 1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA |          |           | Event Created By Automation Framework |
     
    @C87058 @SRM
    Scenario Outline: Verify user can start a mission from a preplan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on add new event plan button to create a new event plan
    And Enters valid values for the following fields
      | EventPlan    | <eventPlan>    |
      | MissionType | <missionType> |
      | Stream      | <stream>      |
      | Address     | <address>     |
      | Latitude    | <latitude>    |
      | Longitude   | <longitude>   |
      | Description | <description> |
    Then A new event plan with name "<eventPlan>" should get created successfully
    When User clicks on launch event plan button "<eventPlan>"
    Then New event creation form should load successfully
    And Enters valid values for the following fields and "<participants>" to create event from preplan
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
      | username          	 | password   | eventPlan                 |incident           			    | missionType     |stream| address                                                       | latitude | longitude | description                           |participants    |
       | z-controller@ee.io	 | Password1@ | TestEventPlanFromPrePlan11 |AutomationTestEventFromEventPlan1| Search & Rescue |None  | 1001 Broken Sound Parkway NW,Suite C,33487,Boca Raton,FLORIDA |          |           | Event Created By Automation Framework |Z-EEiUser Z-User|
 @C87059 @SRM
 Scenario Outline: Verify user can not start a mission from a preplan if already assigned to an active event  
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on launch event plan button "<eventPlan>"
    Then can not create new event error message should be displayed
    And Clean up test event "<incident>"
    And Clean up test event plan "<eventPlan>"
    
    Examples: 
      | username           | password   | eventPlan                |incident						 |
      | z-controller@ee.io | Password1@ | TestEventPlanFromPrePlan11 |AutomationTestEventFromEventPlan1|
      
  @C46771 @SRM
  Scenario Outline: Verify user can share the event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on share button to share "<eventPlan>" event plan
    Then event plan "<eventPlan>" should get shared successfully

    Examples: 
      | username           | password   | eventPlan                |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan3 |

  @C46772 @SRM
  Scenario Outline: Verify users can access the shared event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    Then user should see the shared "<eventPlan>" plan

    Examples: 
      | username           | password   | eventPlan                |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan3 |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan1 |
     @C86295 @SRM 
    Scenario Outline: Verify users does not have access to archive the event plan shared by other user
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    Then User should should not have access to archive the event plan "<eventPlan>" shared by other user

    Examples: 
      | username           | password   | eventPlan                |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan1 |


  @C46769 @SRM
  Scenario Outline: Verify user can archive the event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on archive button to archive "<eventPlan>" event plan
    Then event plan "<eventPlan>" should get archived successfully

    Examples: 
      | username           | password   | eventPlan                |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan3 |

  @C46770 @SRM
  Scenario Outline: Verify user can unarchive the event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User navigates to archived event plan tab
    And User clicks on unarchive button of "<eventPlan>" to activate the plan
    Then event plan "<eventPlan>" should get activated successfully

    Examples: 
      | username           | password   | eventPlan                |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan3 |

  @C46769 @SRM @Smoke
  Scenario Outline: Verify user can archive the event plan
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Event plan tab
    And User clicks on archive button to archive "<eventPlan>" event plan
    Then event plan "<eventPlan>" should get deleted successfully

    Examples: 
      | username           | password   | eventPlan                |
      | z-controller@ee.io | Password1@ | AutomationTestEventPlan1 |
      | z-autobot@ee.io    | Password1@ | AutomationTestEventPlan2 |
      | z-user@ee.io       | Password1@ | AutomationTestEventPlan3 |

  #Examples:
  #	|username     |password|incidentName|
  #	|yogi@msys.com|Password1@|TestEvent111|
 
  @SRM  @C73390 @ignore
  Scenario Outline: Verify user can add event case number after an event.
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on add new event button to create a new event
    And Enters valid values for the following fields and "<participants>"
      | Incident    | <incident>    |
      | MissionType | <missionType> |
      | Stream      | <stream>      |
      | Address     | <address>     |
      | Latitude    | <latitude>    |
      | Longitude   | <longitude>   |
      | Description | <description> |
    And A new event with name "<incident>" should get created successfully
    #And User will be able to change the Case Number successfully
    #Then User will be able to rejoin the event "<incident>"

    Examples: 
      | username        | password   | incident                | missionType     | stream | address                                                       | latitude | longitude | description                           | participants                                          |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident3 | Search & Rescue | None   | 1001 Broken Sound Parkway NW Suite C 33487 Boca Raton FLORIDA |          |           | Event Created By Automation Framework | Z-User Z-Controller, Z-User Z-Viewer,Z-EEiUser Z-User |

  @C44558 @SRM @ignore
  Scenario Outline: Verify user can end the event successfully
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User clicks on the active event "<incidentName>" link to join
    Then User joins the event successfully
    And User should be able to end an active event "<incidentName>" successfully

    Examples: 
      | username        | password   | incidentName            |
      | z-autobot@ee.io | Password1@ | AutomationTestIncident3 |
  
  
  @SRM @C73391 @ignore
  Scenario Outline: Verify user can add Critical Incident as mission type while creating a new event.
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
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
    #Then A new event with name "<incident>" and type "<missionType>" should get created successfully

    Examples: 
      | username           | password   | incident                | casenumber              | missionType       | stream | address                                                         | latitude | longitude | description                           | participants                     |
      | z-controller@ee.io | Password1@ | AutomationTestIncident4 | AutomationTestIncident4 | Critical Incident | None   | 1001 Broken Sound Parkway NW, Suite C, 33487 Boca Raton,FLORIDA |          |           | Event Created By Automation Framework | Z-User Z-Viewer,Z-EEiUser Z-User |


