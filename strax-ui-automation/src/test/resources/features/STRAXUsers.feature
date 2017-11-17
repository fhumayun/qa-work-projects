Feature: STRAX Users functionality

  @SRM @Users @C44547
  Scenario Outline: Verify user search
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    Then User Should be able to search for a user "<userToSearch>"

    Examples: 
      | username           | password   | userToSearch       |
      | z-controller@ee.io | Password1@ | z-controller@ee.io |
      | z-autobot@ee.io    | Password1@ | z-autobot@ee.io    |

  @SRM @Users @C44548 @Smoke
  Scenario Outline: Verify add new user
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    And clicks on Add New User button
    And User Enters valid values for the following fields and click save button:
      | Email       | <email>       |
      | FirstName   | <firstname>   |
      | LastName    | <lastname>    |
      | NewPassword | <newpassword> |
      | Role        | <role>        |
      | Color       | <color>       |
      | Device      | <device>      |
    Then Then User should get created successfully with email "<email>"

    Examples: valid user details
      | username           | password   | email                       | firstname    | lastname | newpassword    | role       | color | device |
      | z-controller@ee.io | Password1@ | z-automationtestuser1@ee.io | Z-Automation | Z-Tester | Password1~!@#  | User       | Green | none   |
      | z-autobot@ee.io    | Password1@ | z-automationtestuser2@ee.io | Z-Automation | Z-Tester | Password1$%*-_ | Controller | Green | none   |
      | z-autobot@ee.io    | Password1@ | z-automationtestuser3@ee.io | Z-Automation | Z-Tester | Password1+=.^& | Viewer     | Green | none   |

  @SRM @Users @C44549
  Scenario Outline: Verify edit user
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    And Selects user to edit "<email>"
    And User Enters valid values for the following fields and click update button:
      | Email     | <email>     |
      | FirstName | <firstname> |
      | LastName  | <lastname>  |
      | Role      | <role>      |
      | Color     | <color>     |
      | Device    | <device>    |
    Then Then User should get updated successfully

    Examples: 
      | username           | password   | email                       | firstname    | lastname | role | color | device |
      | z-controller@ee.io | Password1@ | z-automationtestuser1@ee.io | Z-Automation | Z-Tester | User | Green | QA1    |
      | z-autobot@ee.io    | Password1@ | z-automationtestuser2@ee.io | Z-Automation | Z-Tester | User | Green | hi     |

  @SRM @Users @C44550 @Smoke
  Scenario Outline: Verify archive user
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    And Selects user to archive "<user>"
    Then Then User "<user>" should get archived successfully

    Examples: 
      | username           | password   | user                        |
      | z-controller@ee.io | Password1@ | z-automationtestuser1@ee.io |
      | z-autobot@ee.io    | Password1@ | z-automationtestuser2@ee.io |
      | z-autobot@ee.io    | Password1@ | z-automationtestuser3@ee.io |

  @SRM @Password @C44631
  Scenario Outline: Verify password strength policy is enforced
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Users menu
    And clicks on Add New User button
    And User enters value for "<testPassword>" field
    Then following "<errorMessage>" should be displayed

    Examples: 
      | username           | password   | testPassword | errorMessage                                                                                                                                             |
      | z-controller@ee.io | Password1@ | eei          | Password too short. Must be at least 8 characters long.                                                                                                  |
      | z-controller@ee.io | Password1@ | password     | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | Password@    | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | Password1    | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | P@SSWORD1    | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | p@ssword1    | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | P@$$WORD     | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      | z-controller@ee.io | Password1@ | p@$$word     | Password does not contain minimum 4 character sets, use, Uppercase (A-Z), Lowercase (a-z), Numeric (0-9), Special Characters ~ ! @ # $ % * - _ + = . ^ & |
      
      
     @SRM @Password @C80776
     Scenario Outline: As a user I want to be able to change my Strax password so that I can ensure my account security
     Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User clicks on Username header field 
And then clicks on the Change password menu
And User enters valid "<newpassword>" compliant with Strax security policy
Then Password change should be successful
   Examples: 
      | username           | password   | newpassword |
      | z-user@ee.io    | Password1@ | Password1@ |
