@event @happy
Feature: Create an event using the SRM
  As a PIC
  I want to create a new event using the SRM

  @create
  Scenario: As a PIC I should be able to create a new event
    Given The PIC is logged in
    When The PIC assigns a UAS
    And The PIC assigns team members
    And The PIC creates a new event
    Then A new event should be created

  @update
  Scenario: As a PIC I should be able to update the event
    Given The PIC has new information
    When The PIC updates the event
    Then The event should reflect the changes

  @read
  Scenario: As a PIC I should be able to read the event data
    Given The PIC needs to read the event data
    When The PIC requests the data
    Then The PIC should recieve the event information

  @delete
  Scenario: As a PIC I should be able to delete an event when it is over
    Given The event is over
    When The PIC deletes the event
    Then It should no longer be considered active