Feature: Basic Test

  Background:

  Scenario: Action dispatch test

    Given the following animals: cow, horse, sheep

    When I decide to eat the cow

    Then I am waiting 2000 ms

    Then It remains only 2 animals



