@fidget @happy
Feature: Create, update, and manage fidgets
  As an admin
  I should be able to create, update, and read fidgets in the system

  @create
  Scenario: As an admin I should be able to create a new Fidget
    Given I have all the required fidget information
    When I create a new fidget
    Then I should get a fidget creation successful response

  @update
  Scenario: As an admin I should be able to update an existing Fidget
    Given I have new Fidget information
    When I update the Fidget
    Then I should see the updated Fidget

  @read
  Scenario: As an admin I should be able to look up a Fidget by id
    Given I need to look up a Fidget and have the id
    When I look the Fidget up
    Then I should get the Fidget profile back

  @delete
  Scenario: As an admin I should be able to delete decommissioned Fidgets
    Given I need to delete a decommissioned Fidget and have the id
    When I delete the Fidget
    Then I should no longer be able to use the Fidget
