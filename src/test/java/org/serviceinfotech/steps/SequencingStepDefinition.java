package org.serviceinfotech.steps;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.serviceinfotech.controller.AlternateAlgorithmController;
import org.serviceinfotech.controller.ColourAlgorithmController;
import org.serviceinfotech.controller.Controller;
import org.serviceinfotech.controller.SequenceAlgorithmController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SequencingStepDefinition extends StepDefinitionBase {

    Controller controller;

    @Before
    public void beforeScenario(){
       buildFixture();
    }

    @Given("^I have (\\d+) lights in alternating colours of$")
    public void iHaveLightsInAlternatingColoursOf(int numberOfLights, DataTable dataTable) throws Throwable {
        List<String> lightColors = dataTable.asList(String.class);

    }

    @When("^I request (.*) algorithm execution$")
    public void iRequestSequences(String typeOfSequence) throws Throwable {
        switch (typeOfSequence) {
            case "Sequence":
                controller = new SequenceAlgorithmController(fixture);
                break;
            case "Colour":
                controller = new ColourAlgorithmController(fixture);
                break;
            case "Alternate":
                int timesToAlternate =2;
                controller = new AlternateAlgorithmController(fixture,timesToAlternate);
                break;
             default:
                 throw new IllegalArgumentException("Invalid Algorithm Sequence");
        }


    }


    @Then("^I see each light is turned on for (.*) seconds then off in turn from first to last.$")
    public void iSeeEachLightIsTurnedOnForSecondsThenOffInTurnFromFirstToLast(String waitTime) throws Throwable {

        ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
        taskExecutor.submit(controller);

        assertLightingSequence();

        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }





    @Then("^I see Group of like coloured lights are turned on and off every (\\d+) second$")
    public void iSeeGroupOfLikeColouredLightsAreTurnedOnAndOffEverySeconds(int time) throws Throwable {

        ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
        taskExecutor.submit(controller);

        assertColouringSequence();

        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }



    @Then("^I see all algorithm running alternately$")
    public void iSeeAllAlgorithmRunningAlternately() throws Throwable {
        ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
        taskExecutor.submit(controller);

        assertLightingSequence();
        assertColouringSequence();
        assertLightingSequence();
        assertColouringSequence();

        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }


}
