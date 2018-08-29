Feature: STRAX Map Layers functionality

@map1 @Smoke
Scenario Outline: verify Map Layers API returns the valid response
	Given The STRAX map layer API is authenticated with user "<username>" and "<password>"
	When User request the map layers information with GET method
	Then The map layer API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
 @map12 
Scenario Outline: verify gis icon API returns the valid response
	Given The STRAX map layer API is authenticated with user "<username>" and "<password>"
	When User request all the gis icons with GET method
	Then The map layer API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |
  @map3 @Smoke
Scenario Outline: verify Map Layers API returns the valid response
	Given The STRAX map layer API is authenticated with user "<username>" and "<password>"
	When User request to create a new map layers with POST method
	Then The map layer API should return valid response and status as 200
	
	Examples: valid username/password combination
      | username        | password   |
      | z-autobot@ee.io | Password1@ |