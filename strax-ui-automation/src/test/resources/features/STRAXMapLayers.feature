Feature: STRAX Map Layers functionality

@C73383 @SRM
Scenario Outline: Verify users have access to create a new map layer
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to MapLayers menu
Then User should have access to create a new map layer

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|
	|z-controller@ee.io|Password1@|
