Feature: Sequencing Algorithms

  @wip
  Scenario: Sequence each light is turned on for half a second then off in turn from first to last
    Given I have 20 lights in alternating colours of
      | RED   |
      | GREEN |
      | WHITE |
    When  I request Sequence algorithm execution
    Then  I see each light is turned on for 0.5 seconds then off in turn from first to last.

  Scenario: Colour - all of the red lights are turned on for 1 second, then all the green for 1 second then all the white for 1 second
    Given I have 20 lights in alternating colours of
      | RED   |
      | GREEN |
      | WHITE |
    When  I request Colour algorithm execution
    Then  I see Group of like coloured lights are turned on and off every 30 seconds
#    Then  I see all RED lights are ON
#    Then  I see all WHITE lights are OFF
#    Then  I see all GREEN lights are OFF
#    And   I wait for 1 second
#    Then  I see all GREEN lights are ON
#    Then  I see all RED lights are OFF
#    Then  I see all WHITE lights are OFF
#    Then  I see all GREEN lights are ON
#    And   I wait for 1 second
#    Then  I see all RED lights are OFF
#    Then  I see all GREEN lights are OFF

  Scenario: Alternate - the 'sequence' algorithm runs for 30 seconds, then the 'colour' algorithm for 30 seconds.
    Given I have 20 lights in alternating colours of
      | red   |
      | green |
      | white |
    When  I request Alternate algorithm execution
    And   I wait for 30 second
    Then  I see Sequence algorithm running
    When  I wait for 30 second
    Then  I see Colour algorithm running


