Feature: STRAX Account functionality

@acc
Scenario Outline: verify Accounts API returns the valid response
	Given The STRAX Account API is authenticated with user "<username>" and "<password>"
	When User reuests the accounts information with GET method
	Then The API should return valid response and status as 200

Examples: valid username/password combination
      | username           | password   |
      | z-autobot@ee.io    | Password1@ |