Feature: STRAX Login functionality 
@C26453 @SRM @Login
Scenario: Verify title bar reads STRAX(tm)
    Given The STRAX Application login page is open
    Then The title bar should contain STRAX
@C26454 @SRM  @Login
Scenario Outline: Verify footer information is correct
    Given The STRAX Application login page is open
    Then The footer copyright should contain "<footerString>"
    
Examples: footer string
	|footerString|
	|© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865|
@C26457 @SRM @Login
Scenario Outline: Login with valid credentials
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then The Login should be successful
    
Examples: valid username/password combination
	|username     |password|
	|yogi@msys.com|eei  |
@C26458 @SRM @Login
Scenario Outline: Login form rejects invalid credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login should be failed  
Examples: Invalid username/password combination
	|username     |password|
	|yogi@msys.com|eei123  |
	|yogi@invalid.com		  |eei    |
@C26463 @SRM @Login
Scenario Outline: Login form rejects malformed/blank credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login page should reject the credentials
Examples: malformed/blank username/password combination
	|username     |password|
	|yogi@msys|eei|
	||eei|
	|yogi@msys.com||
	
@C26460	@SRM @Login
Scenario Outline: Login form should respond to the Enter and Tab keys 
	Given The STRAX Application login page is open
	When user navigates to useremail field using tab key and enters "<useremail>"  
	And user navigates to password field using tab key and enters "<password>" 
	And user hits Enter key
	Then The Login should be successful
Examples: valid useremail & password
	|useremail|password| 
	|yogi@msys.com|eei|

@C26465	@SRM  @Permissions
Scenario Outline: Verify user permissions to menu items 
	Given The STRAX Application login page is open
	When User Enters Valid "<useremail>" and "<password>" 
	Then The user should have access to these "<menu>" 
Examples: valid useremail & password
	|useremail|password|menu|
	|controller@ee.io|eei|Events, Users, UASs, Trackers|
	|autobot@ee.io|eei|Events, Users, UASs, Trackers|
	|user@ee.io|eei|Events|
	|viewer@ee.io|eei|Events|
@C26466 @SRM @mytest @Permissions
Scenario Outline: Verify user can access account settings menu option 
	Given The STRAX Application login page is open
	When User Enters Valid "<useremail>" and "<password>" 
	Then The user should have access to account settings menu 
Examples: valid useremail & password
	|useremail|password|
	|controller@ee.io|eei|
	|autobot@ee.io|eei|
@C26467 @SRM @mytest @Permissions
Scenario Outline: Verify user can not access account settings menu option 
	Given The STRAX Application login page is open
	When User Enters Valid "<useremail>" and "<password>" 
	Then The user should not have access to account settings menu 
Examples: valid useremail & password
	|useremail|password|
	|user@ee.io|eei|
	|viewer@ee.io|eei|


	
