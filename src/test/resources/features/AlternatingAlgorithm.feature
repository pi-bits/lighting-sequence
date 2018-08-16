Feature: Fairy Lights Sequencing
Background: There are 20 fairy lights in alternating colours red, green and white that can be independently turned on and off. The controller is a java program that is given the name of a sequencing algorithm for turning the lights on and off. When launched, the program will execute the requested sequencing algorithm until it is terminated. As each light is turned on or off, the program should output the message 'Light <number> <colour> <on/off>', for example 'Light 1 red on'.

  Scenario: Alternate sequence algorithm every 30 seconds
    Given I have 20 lights in alternating colours of
      | RED   |
      | GREEN |
      | WHITE |
    When  I request Alternate algorithm execution
    Then  I see all algorithm running alternately


