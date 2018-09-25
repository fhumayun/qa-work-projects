Feature: STRAX Participants functionality

@user1 @Smoke @C171592
Scenario Outline: verify participant API works correctly for creating a participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests the create new participant with POST method with valid data
	Then The new participant should get created and return status code as 200
		Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
	
@user2 @ignore @C171593
Scenario Outline: verify participant API works correctly for updating an existing participant
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User updates participant "<loginId>" details with PUT method with valid data
	Then The participant details should get updated successfully and return status code as 200
			Examples: valid username/password combination
      | username        | password   |loginId					|
      | z-autobot@ee.io | Password1@ |z-apitest@ee.io |
	
@user3 @C171594
Scenario Outline: verify GET single participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User request single participant "<loginId>" with GET method with valid participantDocId
	Then The participant API should return single participant details and return status code as 200
			Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-controller@ee.io|
	
@user4 @C171595
Scenario Outline: verify unlock participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User reuests the unlock a participant "<loginId>" with POST method with valid participantDocId
	Then The participant should get unlocked and return status code as 200
				Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|
@user5 @Smoke @C171596
Scenario Outline: verify change password API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User change password of a participant "<loginId>" with POST method
	Then The password should get changed successfully and return status code as 200
				Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|
@user6 @Smoke @C171597
Scenario Outline: verify delete participant API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User deletes a participant "<loginId>" with DELETE method with valid participantDocId
	Then The participant should get deleted and return status code as 200
			Examples: valid username/password combination
      | username           | password        |loginId        |
      | z-autobot@ee.io    | Password1@      |z-apitest@ee.io|

@C171625
Scenario Outline: verify GET callsign API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests callsigns information with GET method
	Then The callsigns API should return all available callsigns in response and return status code as 200
			Examples: valid username/password combination
      | username           | password        |
      | z-autobot@ee.io    | Password1@      |

@C171639
Scenario Outline: verify POST callsign API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests to creates new callsign and assign to a user "<username>"
	Then The callsign should get created and return status code as 200
			Examples: valid username/password combination
      | username           | password        |
      | z-autobot@ee.io    | Password1@      |

@C171640
Scenario Outline: verify PUT callsign API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests to assign the callsign "<callsign>" to other user "<username>"
	Then The callsign should get assigned to other user and return status code as 200
			Examples: valid username/password combination
      | username        | password  |callsign|
      | z-autobot@ee.io | Password1@|Tango6  |

@C171641
Scenario Outline: verify archive callsign API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests to archive the callsign "<callsign>"
	Then The callsign should get archived and return status code as 200
			Examples: valid username/password combination
      | username        | password  |callsign|
      | z-autobot@ee.io | Password1@|Tango6  |

@C171642
Scenario Outline: verify unarchive callsign API works correctly
	Given The STRAX API is authenticated with user "<username>" and "<password>"
	When User requests to unarchive the callsign "<callsign>"
	Then The callsign should get unarchived and return status code as 200
			Examples: valid username/password combination
      | username        | password  |callsign|
      | z-autobot@ee.io | Password1@|Tango6  |
