Feature: STRAX Map Layers functionality

@ignore
Scenario Outline: verify Map Layers API returns the valid response
	Given The STRAX map layer API is authenticated with user "<username>" and "<password>"
	When User reuests the map layers information with GET method
	Then The map layer API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |