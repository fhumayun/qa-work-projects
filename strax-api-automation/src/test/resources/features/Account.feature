Feature: STRAX Account functionality


Scenario: verify Accounts API returns the valid response
	Given The STRAX Accounts API end point is available
	When User reuests the accounts information with GET method
	Then The API should return valid response and status as 200