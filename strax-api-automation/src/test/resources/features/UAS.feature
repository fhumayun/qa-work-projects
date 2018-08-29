Feature: STRAX UAS/fidget functionality

@testuas @Smoke
Scenario Outline: verify UAS API returns the valid response
	Given The STRAX UAS API is authenticated with user "<username>" and "<password>"
	When User request the UAS information with GET method
	Then The UAS API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
