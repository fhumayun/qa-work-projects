Feature: STRAX Users functionality
@SRM @Users @C44547
Scenario Outline: Verify user search 
    Given The STRAX application is loggedin with "<username>" and "<password>"
    When User navigates to Users menu
    Then User Should be able to search for a user "<userToSearch>"
Examples: 
	|username     |password|userToSearch|
	|controller@ee.io|eei  |controller@ee.io|
	|autobot@ee.io|eei|autobot@ee.io|
 @SRM @Users @C44548
Scenario Outline: Verify add new user  
    Given The STRAX application is loggedin with "<username>" and "<password>"
    When User navigates to Users menu
    And clicks on Add New User button
    And User Enters valid values for the following fields and click save button:
      	|Email|<email>|
    	|FirstName|<firstname>|
    	|LastName|<lastname>|
    	|NewPassword|<newpassword>|
    	|Role|<role>|
    	|Color|<color>|
    	|Device|<device>|
    Then Then User should get created successfully with email "<email>"
Examples: valid user details
	|username     |password|email|firstname|lastname|newpassword|role|color|device|
	|controller@ee.io|eei  |AutomationTestUser1@ee.io|Automation1|Tester1|Auto123|User|Green|none|
	|autobot@ee.io|eei|AutomationTestUser2@ee.io|Automation1|Tester1|Auto123|Controller|Green|none|
@SRM @Users @C44549
Scenario Outline: Verify edit user  
    Given The STRAX application is loggedin with "<username>" and "<password>"
    When User navigates to Users menu
    And Selects user to edit "<email>"
    And User Enters valid values for the following fields and click update button:
      	|Email|<email>|
    	|FirstName|<firstname>|
    	|LastName|<lastname>|
    	|Role|<role>|
    	|Color|<color>|
    	|Device|<device>|
    Then Then User should get updated successfully
Examples: 
	|username     |password|email|firstname|lastname|role|color|device|
	|controller@ee.io|eei  |AutomationTestUser1@ee.io|Automation1|Tester1|User|Green|QA1|
	|autobot@ee.io|eei  |AutomationTestUser2@ee.io|Automation1|Tester1|User|Green|hi|
@SRM @Users @C44550
Scenario Outline: Verify archive user  
    Given The STRAX application is loggedin with "<username>" and "<password>"
    When User navigates to Users menu
    And Selects user to archive "<user>"
    Then Then User "<user>" should get archived successfully
Examples: 
	|username     |password|user|
	|controller@ee.io|eei  |AutomationTestUser1@ee.io|
	|autobot@ee.io|eei  |AutomationTestUser2@ee.io|
 