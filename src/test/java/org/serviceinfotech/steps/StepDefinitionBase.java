package org.serviceinfotech.steps;

import cucumber.api.java.Before;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.serviceinfotech.controller.ColourAlgorithmController;
import org.serviceinfotech.controller.SequenceAlgorithmController;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.serviceinfotech.controller.ColourAlgorithmController.COLOUR_SEQ_TIMER;
import static org.serviceinfotech.controller.SequenceAlgorithmController.LIGHTING_SEQ_TIMER;

public class StepDefinitionBase {

    LightingFixture fixture;

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

    protected void assertColouringSequence() throws InterruptedException {
        for (int i = 0; i < Colour.values().length; i++) {
            assertLightStatusByColour(Colour.values()[i], State.ON);
            Thread.sleep(COLOUR_SEQ_TIMER);
            assertLightStatusByColour(Colour.values()[i], State.OFF);
        }
    }

    protected void assertLightingSequence() {
        fixture.getLightBulbs().forEach(lightBulb -> {
            try {
                Assert.assertThat(lightBulb.getState(), Is.is(State.ON));
                Thread.sleep(LIGHTING_SEQ_TIMER);
                Assert.assertThat(lightBulb.getState(), Is.is(State.OFF));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    protected void assertLightStatusByColour(Colour colour, State state) {
        getLightBulbsByColour(colour).stream().forEach(lightBulb -> {
            Assert.assertThat(lightBulb.getColour().getColour() + " should be :" + state.name(), lightBulb.getState(), Is.is(state));
        });
    }

    protected void buildFixture(){
        fixture = new LightingFixture( buildAlternativeLighBulbFixture(20));
    }

    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return fixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }
}
