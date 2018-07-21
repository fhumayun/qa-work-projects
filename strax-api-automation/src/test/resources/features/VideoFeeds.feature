Feature: STRAX Map Layers functionality

@ignore
Scenario Outline: verify video feed API returns the valid response
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User reuests the video feed information with GET method
	Then The video feed API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |