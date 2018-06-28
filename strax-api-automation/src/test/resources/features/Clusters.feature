Feature: STRAX Event functionality


Scenario: verify cluster API returns the valid response
	Given The STRAX cluster API end point is available
	When User reuests the events information with GET method
	Then The cluster API should return valid response and status as 200