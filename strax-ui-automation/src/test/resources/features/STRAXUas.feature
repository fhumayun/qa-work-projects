Feature: STRAX UASs functionality
@C44551
Scenario Outline: Verify user have access to create a new UAS
Given The STRAX application is loggedin with "<username>" and "<password>"
When User navigates to UASs menu
Then User should have access to create a new UAS

Examples:
	|username     |password|
	|autobot@ee.io|eei|
@C44552	
Scenario Outline: Verify user does not have access to create a new UAS
Given The STRAX application is loggedin with "<username>" and "<password>"
When User navigates to UASs menu
Then User should not have access to create a new UAS

Examples:
	|username     |password|
	|controller@ee.io|eei  |


