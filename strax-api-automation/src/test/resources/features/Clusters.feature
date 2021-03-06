Feature: STRAX Cluster API

@Cluster @C171598 @Smoke
Scenario Outline: verify GET cluster API returns the valid response
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User request the events information with GET method
	Then The cluster API should return all available clusters in response and return status code as 200
				Examples: valid username/password combination
      | username           | password|
      | z-autobot@ee.io | Password1@ |
 @Cluster @C171599 @Smoke
Scenario Outline: verify POST cluster API successfully creates a cluster
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User requests the create new cluster with POST method with valid data
	Then The cluster API should create a new cluster and return status as 200
				Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
 @Cluster @C171600
Scenario Outline: verify PUT cluster API successfully updates a cluster
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User requests to update an existing cluster "<eventName>" with PUT method with valid data
	Then The cluster API should update the cluster and return status as 200
				Examples: valid username/password combination
      | username           | password   |eventName|
      | z-autobot@ee.io    | Password1@ |EventFromAPI1|

@callsign   @ignore  
Scenario Outline: verify GET cluster API pulls the callsign name in participant list
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User requests the events information of "<eventName>" with GET method
	Then The GET cluster API should return all the cluster details and return status code as 200
  And The participant list should have callsign name for "<username>" along with other details
				Examples: valid username/password combination
      | username           | password   |eventName    |
      | z-autobot@ee.io    | Password1@ |EventFromAPI1|
 @Cluster @ignore @C171601
Scenario Outline: verify join active event API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User"<participant>" requests the join cluster "<eventName>" with POST method with valid data
	Then The join event API should add a participant to active event and return status as 200
				Examples: valid username/password combination
      | username           | password   |eventName		|
      | z-controller@ee.io    | Password1@ |EventFromAPI1|
@chat @Smoke @C171602
Scenario Outline: verify GET chat API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests get chat of an event "<eventName>" with GET method
	Then The chat API should return all chat messages of an event and return status code as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1|
@notes @C171603
Scenario Outline: verify GET notes API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests notes of an event "<eventName>" with GET method
	Then The notes API should return all notes of an event and return status code as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1|
@geotag @Smoke @C171604
 Scenario Outline: verify GET Geotags API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests Geotags of an event "<eventName>" with GET method
	Then The notes API should return all Geotags of an event and return status code as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1| 
@C171605  
Scenario Outline: verify GET event scribe notes API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User requests scribe notes of an event with GET method for "<eventName>" and "<username>"
	Then The scribe notes API should return all available notes and return status code as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1|  
      

@C171606 @log1
Scenario Outline: verify POST method of event scribe notes API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User requests to add a new scribe notes to an event with POST method for "<eventName>" and "<username>"
	Then The scribe notes API should add a new note to the event and return status code as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1| 
 

@C171644
Scenario Outline: As a STRAX user i want an API with POST method to promote a scribe entry to chat channel
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User "<username>" requests to promote a scribe note to chat channel for "<eventName>" with valid request body using POST method
	Then the response should have JSON <promoted> object which should have a boolean property <chat>
	And return the statusCode as 200
			Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1|

@C171654
Scenario Outline: verify PUT preferences API works correctly for browser property
	Given The STRAX Cluster API is authenticated with authTicket with user "<username>" and "<password>"
	When User reuests to add preferences with PUT method for browser property
	Then The preferences API should add user preference and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
@C171655     
Scenario Outline: verify PUT preferences API works correctly for android property
	Given The STRAX Cluster API is authenticated with authTicket with user "<username>" and "<password>"
	When User reuests to add preferences with PUT method for android property
	Then The preferences API should add user preference for android and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
      
@C171656     
Scenario Outline: verify PUT preferences API works correctly for map property
	Given The STRAX Cluster API is authenticated with authTicket with user "<username>" and "<password>"
	When User reuests to add preferences with PUT method for map property
	Then The preferences API should add user preference for map and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |

@C171657
Scenario Outline: verify GET preferences API works correctly
	Given The STRAX Cluster API is authenticated with authTicket with user "<username>" and "<password>"
	When User reuests preferences details with GET method
	Then The preferences API should return currently set preferences and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
      
@C171658   
Scenario Outline: verify DELETE preferences API works correctly
	Given The STRAX Cluster API is authenticated with authTicket with user "<username>" and "<password>"
	When User reuests to delete preferences with DELETE method
	Then The preferences API should delete user preference and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |


      
@Cluster @C171607 @Smoke
Scenario Outline: verify delete cluster API deletes the given cluster
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User deletes the events "<eventName>" with DELETE method
	Then The delete cluster API should delete the cluster and return status code as 200
					Examples: valid username/password combination
      | username         	   | password   |eventName		|
      | z-autobot@ee.io			 | Password1@ |EventFromAPI1|
	
@ignore @C171608
Scenario Outline: verify IER Alert Escalation API send event ID if user already assigned
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User escalate an IER to active event when user is already assigned to an active event
	Then the API should return status code as 500
					Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |

@C171609
Scenario Outline: verify GET event message API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests event messages details with GET method
	Then The event message API should return all available messages and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
      
