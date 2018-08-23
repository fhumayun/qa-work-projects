Feature: STRAX pre plan events functionality

@ignore
Scenario Outline: verify GET pre plan API returns the valid response
	Given The STRAX pre plan API is authenticated with user "<username>" and "<password>"
	When User request the pre plan information with GET method
	Then The pre plan API should return all available pre plans in response and return status code as 200
				Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
 @ignore     
Scenario Outline: verify POST pre plan event API successfully creates a pre plan 
	Given The STRAX pre plan API is authenticated with user "<username>" and "<password>"
	When User requests the create new pre plan with POST method with valid data
	Then The pre plan API should create a new pre plan and return status as 201
				Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |
 @ignore 
Scenario Outline: verify PUT pre plan event API successfully updates a pre plan
	Given The STRAX pre plan API is authenticated with user "<username>" and "<password>"
	When User requests to update an existing pre plan "<eventName>" with PUT method with valid data
	Then The pre plan API should update the pre plan and return status as 200
				Examples: valid username/password combination
      | username           | password   |eventName         |
      | z-autobot@ee.io    | Password1@ |EventPlanFrom API1|
 @ignore 
Scenario Outline: verify delete pre plan API deletes the given pre plan
	Given The STRAX pre plan API is authenticated with user "<username>" and "<password>"
	When User deletes the pre plan "<eventName>" with DELETE method
	Then The delete pre plan API should delete the pre plan and return status code as 200
					Examples: valid username/password combination
      | username         	   | password   |eventName		     |
      | z-autobot@ee.io			 | Password1@ |EventPlanFrom API1|