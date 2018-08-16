package org.serviceinfotech;

import org.serviceinfotech.controller.AlternateAlgorithmController;
import org.serviceinfotech.controller.ColourAlgorithmController;
import org.serviceinfotech.controller.Controller;
import org.serviceinfotech.controller.SequenceAlgorithmController;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.fixture.LightingFixtureBuilder;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=============================");
        System.out.println("*********FAIRY LIGHTS******** ");
        System.out.println("============================= ");
        System.out.println("Choose a Program: ");
        System.out.println("1. ‘Sequence’: each light is turned on for 0.5 seconds then off in turn from first to last. ");
        System.out.println("2. ‘Colour’: all of the red lights are turned on for 1 second, then all the green for 1 second then all the white for 1 second");
        System.out.println("3. ‘Alternate’: the 'sequence' algorithm runs for 30 seconds, then the 'colour' algorithm for 30 seconds.");
        Scanner data = new Scanner(System.in);
        int programNumber = data.nextInt();
        System.out.println("You have choosen Program Number : " + programNumber);
        System.out.println("Enter Number of Lights: ");
        int numberOfLights = data.nextInt();
        Controller controller = null;
        LightingFixture fixture = new LightingFixtureBuilder(numberOfLights,Colour.RED,Colour.GREEN,Colour.WHITE).build();
        switch (programNumber) {
            case 1:
                controller = new SequenceAlgorithmController(fixture);
                break;
            case 2:
                controller = new ColourAlgorithmController(fixture);
                break;
            case 3:
                System.out.println("Enter a number for loop the sequence: ");
                int timesToAlternate = data.nextInt();
                controller = new AlternateAlgorithmController(fixture, timesToAlternate);
                break;
            default:
                throw new IllegalArgumentException("Invalid Algorithm Sequence");
        }

        ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
        taskExecutor.submit(controller);
        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }

}
