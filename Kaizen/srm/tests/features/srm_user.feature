@user @happy
Feature: Create, update, and manage Users
  As a customer
  I want to create a new user account
  Update my information
  and view it at will

  @create
  Scenario: As an administrator i would like to create a new user account
    Given The Admin is logged in and has all the required user information
    When I create a new user account
    Then I should recieve an Account Created message

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
