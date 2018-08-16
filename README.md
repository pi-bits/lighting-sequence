# lighting-sequence


The exercise is to write a controller for a length of fairy lights. There are 20 fairly lights in alternating colours red, green and white that can be independently turned on and off. The controller is a java program that is given the name of a sequencing algorithm for turning the lights on and off. When launched, the program will execute the requested sequencing algorithm until it is terminated. As each light is turned on or off, the program should output the message 'Light <number> <colour> <on/off>', for example 'Light 1 red on'.
The following sequencing algorithms are required:
1. ‘Sequence’: each light is turned on for 0.5 seconds then off in turn from first to last.
2. ‘Colour’: all of the red lights are turned on for 1 second, then all the green for 1 second then all the white for 1 second.
3. ‘Alternate’: the 'sequence' algorithm runs for 30 seconds, then the 'colour' algorithm for 30 seconds.
  
As soon as the algorithm completes, it should start again.

It should be easy to modify the program to support the following changes:

• Add more lights or change the light order.

• Add a new light colour.

• Add new controller algorithms.


How to Build the JAR:
1.Clone the project:
git clone https://github.com/prashantdnaik/lighting-sequence.git

2.In the extracted location:
mvn package -DskipTests

3.Execute the Jar and follow the instructions:
java -jar lighting-sequence/target/lighting-sequence.jar


Note: The project is build on JDK 8 hence JRE8 will be required to run the project.
