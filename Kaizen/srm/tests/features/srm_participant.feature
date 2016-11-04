@participant @happy
Feature: Create, update, and manage Participants
  As an admin
  I should be able to create, update, and read participants in the system

  @create
  Scenario: As an admin I should be able to create a new participant
    Given The Admin is logged in
    And I have all the required participant information
    When I create a new participant
    Then I should get a creation successful response

  @update
  Scenario: As an admin I should be able to update an existing participants information
    Given I have new participant data
    When I update the data
    Then I should get an update successful response

  @read
  Scenario: As an admin I should be able to read a participants information
    Given I need to look up a participant
    When I look up a participant by id
    Then I should get the participant profile back

  @delete
  Scenario: As an admin I should be able to delete a participant profile
    Given I need to delete a participant and I have the id
    When I delete a participant
    Then I should get a deletion successful response
