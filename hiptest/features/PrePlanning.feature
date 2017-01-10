Feature: PrePlanning


  @JIRA-STX-168
  Scenario: Create preplan layout (uid:8465eb57-a6d2-4053-9f2b-c8ac7014702e)
    Given a user successfully logs into the SRM
    And the SRM UI correctly loads
    When the Pre-Mission tab is selected
    And the Create Mission button is pressed
    Then the user should be able to enter the Mission Name
    And pick a Mission Type
    And add a Mission Address
    And provide a Mission Plan Description
    And press Create Mission to save the result for later use
