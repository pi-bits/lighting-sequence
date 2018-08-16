Feature: Fairy Lights Sequencing Lighting and Colouring
Background: There are 20 fairy lights in alternating colours red, green and white that can be independently turned on and off. The controller is a java program that is given the name of a sequencing algorithm for turning the lights on and off. When launched, the program will execute the requested sequencing algorithm until it is terminated. As each light is turned on or off, the program should output the message 'Light <number> <colour> <on/off>', for example 'Light 1 red on'.
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
    Then  I see Group of like coloured lights are turned on and off every 1 second



