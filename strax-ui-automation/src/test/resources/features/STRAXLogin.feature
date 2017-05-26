Feature: STRAX Login functionality 

Scenario: Verify title bar reads STRAX(tm)
    Given The STRAX Application login page is open
    Then The title bar should contain STRAX
    
Scenario Outline: Verify footer information is correct
    Given The STRAX Application login page is open
    Then The footer copyright should contain "<footerString>"
    
Examples: footer string
	|footerString|
	|© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865|

Scenario Outline: Login with valid credentials
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then The Login should be successful
    
Examples: valid username/password combination
	|username     |password|
	|yogi@msys.com|eei  |
    
Scenario Outline: Login with invalid credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login should be failed
    
Examples: Invalid username/password combination
	|username     |password|
	|yogi@msys.com|eei123  |
	|yogi123@msys.com		  |1234    |
