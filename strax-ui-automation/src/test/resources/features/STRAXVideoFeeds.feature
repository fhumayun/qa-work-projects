Feature: STRAX Video Feeds functionality

@C73395 @SRM @VideoFeeds
Scenario Outline: Verify users have access to create a new feed
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Video Feeds menu
Then User should have access to create a new video feed

Examples:
	|username     |password|
	|z-autobot@ee.io|Password1@|