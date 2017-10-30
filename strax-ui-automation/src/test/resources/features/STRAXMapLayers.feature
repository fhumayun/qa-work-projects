Feature: STRAX Map Layers functionality

@C @SRM @MapLayer
Scenario Outline: Verify user have access to create a new map layer
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
Then User should have access to create a new map layer

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|
	|z-controller@ee.io|Password1@|