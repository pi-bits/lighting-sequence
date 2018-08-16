package org.serviceinfotech.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
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


    @After
    private void beforeScenario() throws InterruptedException {

    }


    @Given("^I have (\\d+) lights in alternating colours of$")
    public void iHaveLightsInAlternatingColoursOf(int numberOfLights, DataTable dataTable) throws Throwable {
        List<String> lightColors = dataTable.asList(String.class);
        List<LightBulb> lightBulbs = buildAlternativeLighBulbFixture(numberOfLights);
        fixture = new LightingFixture(lightBulbs);
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
        }


    }


    @And("^I wait for (.*) second")
    public void iWaitForSecond(int waitTime) throws Throwable {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^I wait for (\\d+) seconds")
    public void iWaitForSeconds(int waitTime) throws Throwable {
    }

    @Then("^I see all (.*) lights are ON$")
    public void iSeeAllParticularLightsAreOn(String lightColor) throws Throwable {

        for ( int i = 0; i<Colour.values().length; i++){
            Assert.assertThat(fixture.getLightBulbsByColour(Colour.valueOf(lightColor)).get(i).getState(), Is.is(State.ON));
            Thread.sleep(1000);
        }



    }

    @Then("^I see all (.*) lights are OFF")
    public void iSeeAllParticularLightsAreOff(String lightColor) throws Throwable {


        fixture.getLightBulbs().stream()
                .filter(lightBulb -> lightBulb.getColour().equals(Colour.valueOf(lightColor)))
                .forEach(lightBulb -> {

                    Assert.assertThat(lightBulb.getState(), Is.is(State.OFF));


                });


    }

    @Then("^I see (.*) algorithm running")
    public void iSeeAnAlgorithm(String typeOfSequence) throws Throwable {
    }

    @Then("^I see each light is turned on for (.*) seconds then off in turn from first to last.$")
    public void iSeeEachLightIsTurnedOnForSecondsThenOffInTurnFromFirstToLast(String waitTime) throws Throwable {

        ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
        taskExecutor.execute(controller);

        fixture.getLightBulbs().forEach(lightBulb -> {

            Assert.assertThat(lightBulb.getState(), Is.is(State.ON));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        taskExecutor.shutdown();
        taskExecutor.awaitTermination(10, TimeUnit.SECONDS);

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

    @Then("^I see Group of like coloured lights are turned on and off every (\\d+) seconds$")
    public void iSeeGroupOfLikeColouredLightsAreTurnedOnAndOffEverySeconds(int time) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ;
    }
}
