Feature: STRAX Event functionality


Scenario: verify pre-plan event API returns the valid response
	Given The STRAX pre-plan event API end point is available
	When User reuests the event plan information with GET method
	Then The pre-plan event API should return valid response and status as 200