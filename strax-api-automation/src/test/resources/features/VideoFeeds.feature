Feature: STRAX Map Layers functionality

@ignore
Scenario Outline: verify video feed API returns the valid response
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request the video feed information with GET method
	Then The media API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
@ignore
Scenario Outline: verify mobile feed API returns the valid response
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request the video feed from mobile information with GET method
	Then The media API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |