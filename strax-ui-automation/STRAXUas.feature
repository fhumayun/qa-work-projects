Feature: STRAX UASs functionality
@C44551 @UAS 
Scenario Outline: Verify user have access to create a new UAS
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to UASs menu
Then User should have access to create a new UAS

Examples:
	|username     |password|
	|autobot@ee.io|Password1@|
@C44552 @UAS 
Scenario Outline: Verify user does not have access to create a new UAS
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to UASs menu
Then User should not have access to create a new UAS

Examples:
	|username     |password|
	|controller@ee.io|Password1@|


