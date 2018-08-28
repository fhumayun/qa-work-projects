Feature: STRAX Participants functionality

@user1
Scenario Outline: verify participant API works correctly for creating a participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests the create new participant with POST method with valid data
	Then The new participant should get created and return status code as 200
		Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
	
@user2 @ignore
Scenario Outline: verify participant API works correctly for updating an existing participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User updates participant "<loginId>" details with PUT method with valid data
	Then The participant details should get updated successfully and return status code as 200
			Examples: valid username/password combination
      | username        | password   |loginId					|
      | z-autobot@ee.io | Password1@ |z-apitest@ee.io |
	
@user3
Scenario Outline: verify GET single participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User request single participant "<loginId>" with GET method with valid participantDocId
	Then The participant API should return single participant details and return status code as 200
			Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-controller@ee.io|
	
@user4
Scenario Outline: verify unlock participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User reuests the unlock a participant "<loginId>" with POST method with valid participantDocId
	Then The participant should get unlocked and return status code as 200
				Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|
@user5
Scenario Outline: verify change password API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User change password of a participant "<loginId>" with POST method
	Then The password should get changed successfully and return status code as 200
				Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|
@user6
Scenario Outline: verify delete participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User deletes a participant "<loginId>" with DELETE method with valid participantDocId
	Then The participant should get deleted and return status code as 200
			Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|
