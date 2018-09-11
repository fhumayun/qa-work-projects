Feature: STRAX Map Layers functionality

@vid1 @Smoke @C171618
Scenario Outline: verify video feed API returns the valid response
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request the video feed information with GET method
	Then The media API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
@vid2 @C171619
Scenario Outline: verify mobile feed API returns the valid response
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request the video feed from mobile information with GET method
	Then The media API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |

@vid3 @C171620
Scenario Outline: verify video feed API creates a new video feed
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request to create a new video feed with POST method
	Then The video feed API should create a new feed and return status as 201
		Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
@vid4 @ignore
Scenario Outline: verify video feed API updates an existing video feed
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request to update a existing video feed "<feedName>" with PUT method
	Then The video feed API should update an existing feed and return status as 201
	
	Examples: valid username/password combination
      | username        | password   |feedName   |
      | z-autobot@ee.io | Password1@ |QA-API-Feed|
 @vid5 @C171622
Scenario Outline: verify delete video feed API deletes a video feed
	Given The STRAX video feed API is authenticated with user "<username>" and "<password>"
	When User request to delete an existing video feed "<feedName>" with DELETE method
	Then The video feed API should delete an existing feed and return status as 201
	
	Examples: valid username/password combination
      | username        | password   |feedName   |
      | z-autobot@ee.io | Password1@ |QA-API-Feed|