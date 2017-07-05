Feature: STRAX Event functionality
@C44553
Scenario Outline: Verify user have access to create a new event
Given The STRAX application is loggedin with "<username>" and "<password>"
Then User should have access to create a new event

Examples:
	|username     |password|
	|autobot@ee.io|eei|
	|controller@ee.io|eei|
	|user@ee.io|eei|
@C44554	
Scenario Outline: Verify user have access to create a new event
Given The STRAX application is loggedin with "<username>" and "<password>"
Then User should not have access to create a new event

Examples:
	|username     |password|
	|viewer@ee.io|eei|