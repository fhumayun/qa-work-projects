Feature: STRAX Login functionality 
@C26453 @SRM
Scenario: Verify title bar reads STRAX(tm)
    Given The STRAX Application login page is open
    Then The title bar should contain STRAX
@C26454 @SRM  @ignore  
Scenario Outline: Verify footer information is correct
    Given The STRAX Application login page is open
    Then The footer copyright should contain "<footerString>"
    
Examples: footer string
	|footerString|
	|© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865|
@C26457 @SRM
Scenario Outline: Login with valid credentials
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then The Login should be successful
    
Examples: valid username/password combination
	|username     |password|
	|yogi@msys.com|eei  |
@C26458 @SRM
Scenario Outline: Login form rejects invalid credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login should be failed  
Examples: Invalid username/password combination
	|username     |password|
	|yogi@msys.com|eei123  |
	|yogi@invalid.com		  |eei    |
@C26463 @SRM
Scenario Outline: Login form rejects malformed/blank credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login page should reject the credentials
Examples: malformed/blank username/password combination
	|username     |password|
	|yogi@msys|eei|
	||eei|
	|yogi@msys.com||
	
@C26460	@SRM 
Scenario Outline: Login form should respond to the Enter and Tab keys 
	Given The STRAX Application login page is open
	When user navigates to useremail field using tab key and enters "<useremail>"  
	And user navigates to password field using tab key and enters "<password>" 
	And user hits Enter key
	Then The Login should be successful
Examples: valid useremail & password
	|useremail|password| 
	|yogi@msys.com|eei|
