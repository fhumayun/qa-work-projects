@user @happy
Feature: Create, update, and manage Users
  As a customer
  I want to create a new user account
  Update my information
  and view it at will

  @create
  Scenario: As a new customer I would like to sign up for an account
    Given I have all the required information
    When I sign up for a new user account
    Then I should be able to access my account

  @update
  Scenario: As an existing customer I should be able to update my account information
    Given I need to update my information
    When I update my profile
    Then I will have new information

  @read
  Scenario: As an existing customer I should be able to read my account information
    Given I need to access my information
    When I log in to my account
    Then I should be able to read my information

  @delete
  Scenario: As a super user I should be able to delete a user account
    Given I need to delete a user account
    When I mark a user as deleted
    Then The account should be deactivated
