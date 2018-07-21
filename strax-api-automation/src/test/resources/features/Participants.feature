Feature: STRAX Participants functionality

@ignore
Scenario Outline: verify participant API works correctly for creating new participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests the create new participant with POST method with valid data
	Then The new participant should get created and return status code as 200
		Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
	
	@ignore
Scenario Outline: verify participant API works correctly for updating an existing participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User updates participant details with PUT method with valid data
	Then The participant details should get updated successfully and return status code as 200
	

Scenario Outline: verify GET single participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User request single participant "<loginId>" with GET method with valid participantDocId
	Then The participant API should return single participant details and return status code as 200
			Examples: valid username/password combination
      | username           | password   |loginId        |
      | yogi@msys.com | Password1@      |z-apitest@ee.io|
	
	@ignore
Scenario Outline: verify unlock participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User reuests the unlock a participant with POST method with valid participantDocId
	Then The participant should get unlocked and return status code as 200
	@ignore	
Scenario Outline: verify delete participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User deletes a participant with DELETE method with valid participantDocId
	Then The participant should get deleted and return status code as 200
		@ignore
Scenario Outline: verify change password API works correctly
	Given The STRAX participant API end point is available
	When User change password of a participant with POST method
	Then The password should get changed successfully and return status code as 200