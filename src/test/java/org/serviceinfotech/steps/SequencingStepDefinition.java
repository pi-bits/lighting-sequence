package org.serviceinfotech.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.serviceinfotech.controller.AlternateAlgorithmController;
import org.serviceinfotech.controller.ColourAlgorithmController;
import org.serviceinfotech.controller.Controller;
import org.serviceinfotech.controller.SequenceAlgorithmController;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SequencingStepDefinition {

    Controller controller;
    LightingFixture fixture;


    @cucumber.api.java.Before
    public void beforeScenario(){
        fixture = new LightingFixture( buildAlternativeLighBulbFixture(20));
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



    private List<LightBulb> buildAlternativeLighBulbFixture(int numberOfLights) {
        List<LightBulb> lightBulbs = new ArrayList<LightBulb>(numberOfLights);
        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        return lightBulbs;
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

    private void assertColouringSequence() throws InterruptedException {
        for (int i = 0; i < Colour.values().length; i++) {
            assertLightStatusByColour(Colour.values()[i], State.ON);
            Thread.sleep(1000);
            assertLightStatusByColour(Colour.values()[i], State.OFF);
        }
    }

    private void assertLightingSequence() {
        fixture.getLightBulbs().forEach(lightBulb -> {
            try {
                Assert.assertThat(lightBulb.getState(), Is.is(State.ON));
                Thread.sleep(500);
                Assert.assertThat(lightBulb.getState(), Is.is(State.OFF));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    private void assertLightStatusByColour(Colour colour, State state) {
        fixture.getLightBulbsByColour(colour).stream().forEach(lightBulb -> {
            Assert.assertThat(lightBulb.getColour().getColour() + " should be :" + state.ordinal(), lightBulb.getState(), Is.is(state));
        });
    }
}
