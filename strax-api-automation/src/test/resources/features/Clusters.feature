Feature: STRAX Cluster API

@Cluster @ignore
Scenario Outline: verify GET cluster API returns the valid response
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User request the events information with GET method
	Then The cluster API should return all available clusters in response and return status code as 200
				Examples: valid username/password combination
      | username           | password   |
      | yogi@msys.com | Password1@ |
 @Cluster @ignore
Scenario Outline: verify POST cluster API successfully creates a cluster
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User sends json input to create a new cluster with POST method
	Then The cluster API should return all available clusters in response and return status code as 200
				Examples: valid username/password combination
      | username           | password   |fileName|
      | z-autobot@ee.io    | Password1@ ||
	
@Cluster @ignore
Scenario Outline: verify delete cluster API deletes the given cluster
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User deletes the events with DELETE method
	Then The delete cluster API should delete the cluster and return status code as 200
					Examples: valid username/password combination
      | username           | password   |
      | yogi@msys.com | Password1@ |
	
@IER  @ignore
Scenario Outline: verify IER Alert Escalation API send event ID if user already assigned
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User escalate an IER to active event when user is already assigned to an active event
	Then the API should return status code as 500
					Examples: valid username/password combination
      | username           | password   |
      | yogi@msys.com | Password1@ |
	@ignore
Scenario Outline: verify GET chat API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests chat with GET method
	Then The chat API should return all chat messages and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | yogi@msys.com | Password1@ |
 @ignore     
Scenario Outline: verify GET event plan API works correctly
	Given The STRAX Cluster API is authenticated with user "<username>" and "<password>"
	When User reuests event plan details with GET method
	Then The event plan API should return all available event plans and return status code as 200
			Examples: valid username/password combination
      | username           | password   |
      | yogi@msys.com | Password1@ |