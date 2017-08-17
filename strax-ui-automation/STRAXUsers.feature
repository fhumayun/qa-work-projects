Feature: STRAX Users functionality
@SRM @Users @C44547 
Scenario Outline: Verify user search 
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    Then User Should be able to search for a user "<userToSearch>"
Examples: 
	|username     |password|userToSearch|
	|controller@ee.io|Password1@|controller@ee.io|
	|autobot@ee.io|Password1@|autobot@ee.io|
 @SRM @Users @C44548 
Scenario Outline: Verify add new user  
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
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
	|controller@ee.io|Password1@  |automationtestuser1@ee.io|Automation|Tester|Password1@|User|Green|none|
	|autobot@ee.io|Password1@|automationtestuser2@ee.io|Automation|Tester|Password1@|Controller|Green|none|
@SRM @Users @C44549 
Scenario Outline: Verify edit user  
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
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
	|controller@ee.io|Password1@|automationtestuser1@ee.io|Automation|Tester|User|Green|QA1|
	|autobot@ee.io|Password1@|automationtestuser2@ee.io|Automation|Tester|User|Green|hi|
@SRM @Users @C44550 
Scenario Outline: Verify archive user  
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    And Selects user to archive "<user>"
    Then Then User "<user>" should get archived successfully
Examples: 
	|username     |password|user|
	|controller@ee.io|Password1@|automationtestuser1@ee.io|
	|autobot@ee.io|Password1@|automationtestuser2@ee.io|
 