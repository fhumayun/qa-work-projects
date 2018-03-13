Feature: STRAX Login functionality

  @C26453 @SRM @NoLogout
  Scenario: Verify title bar reads STRAX(tm)
    Given The STRAX Application login page is open
    Then The title bar should contain STRAX

  @C26454 @SRM @NoLogout 
  Scenario Outline: Verify footer information is correct
    Given The STRAX Application login page is open
    Then The footer copyright should contain "<footerString>"

    Examples: footer string
      | footerString                                                                   |
      | © EagleEye Intelligence - 2015/2018 - Customer/Technical Support: 561-894-9865 |

  @C26457 @SRM
  Scenario Outline: Login with valid credentials
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    Then The Login should be successful

    Examples: valid username/password combination
      | username           | password   |
      | z-controller@ee.io | Password1@ |
      | z-autobot@ee.io    | Password1@ |
      | z-user@ee.io       | Password1@ |
      | z-viewer@ee.io     | Password1@ |

  @C26458 @SRM @NoLogout
  Scenario Outline: Login form rejects invalid credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login should be failed

    Examples: Invalid username/password combination
      | username                 | password   |
      | z-controller@ee.io       | eei123     |
      | z-controller@invalid.com | Password1@ |

  @C26463 @SRM @NoLogout
  Scenario Outline: Login form rejects malformed/blank credentials
    Given The STRAX Application login page is open
    When User Enters invalid "<username>" and "<password>"
    Then The Login page should reject the credentials

    Examples: malformed/blank username/password combination
      | username           | password   |
      | z-controller@ee    | Password1@ |
      |                    | Password1@ |
      | z-controller@ee.io |            |

  @C26460 @SRM
  Scenario Outline: Login form should respond to the Enter and Tab keys
    Given The STRAX Application login page is open
    When user navigates to useremail field using tab key and enters "<useremail>"
    And user navigates to password field using tab key and enters "<password>"
    And user hits Enter key
    Then The Login should be successful

    Examples: valid useremail & password
      | useremail       | password   |
      | z-autobot@ee.io | Password1@ |

  @C26465 @SRM @Smoke
  Scenario Outline: Verify user permissions to menu items
    Given The STRAX Application login page is open
    When User Enters Valid "<useremail>" and "<password>"
    Then The user should have access to these "<menu>"

    Examples: valid useremail & password
      | useremail          | password   | menu                                         					|
      | z-controller@ee.io | Password1@ | Events, Users,Map Layers                     					|
      | z-autobot@ee.io    | Password1@ | Events, Users, UASs, Map Layers, Video Feeds, Video Monitor 	|
      | z-user@ee.io       | Password1@ | Events                                      					|
      | z-viewer@ee.io     | Password1@ | Events                                    				    |

  @C26466 @SRM
  Scenario Outline: Verify user can access account settings menu option
    Given The STRAX Application login page is open
    When User Enters Valid "<useremail>" and "<password>"
    Then The user should have access to account settings menu

    Examples: valid useremail & password
      | useremail          | password   |
      | z-controller@ee.io | Password1@ |
      | z-autobot@ee.io    | Password1@ |

  @C26467 @SRM
  Scenario Outline: Verify user can not access account settings menu option
    Given The STRAX Application login page is open
    When User Enters Valid "<useremail>" and "<password>"
    Then The user should not have access to account settings menu

    Examples: valid useremail & password
      | useremail      | password   |
      | z-user@ee.io   | Password1@ |
      | z-viewer@ee.io | Password1@ |

  @C87045 @SRM @NoLogout
  Scenario Outline: Verify user account gets locked out after 5 unsuccessful login attempts
    Given The STRAX Application login page is open
    When User Enters invalid "<useremail>" and "<password>" for consecutive five times
    Then The user account should get locked out

    Examples: valid useremail & password
      | useremail        | password   |
      | yogitest@ee.io   | invalidpass|

