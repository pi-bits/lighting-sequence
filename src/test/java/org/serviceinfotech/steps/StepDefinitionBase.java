package org.serviceinfotech.steps;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.fixture.LightingFixtureBuilder;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;
import java.util.stream.Collectors;

import static org.serviceinfotech.controller.ColourAlgorithmController.COLOUR_SEQ_TIMER;
import static org.serviceinfotech.controller.SequenceAlgorithmController.LIGHTING_SEQ_TIMER;

public class StepDefinitionBase {

    protected LightingFixture fixture;
    protected Integer numberOfLights;
    protected List<Colour> lightColors;



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

    protected void buildFixture() {
        LightingFixtureBuilder lightingFixtureBuilder = new LightingFixtureBuilder(numberOfLights, lightColors.toArray(new Colour[lightColors.size()]));
        this.fixture = lightingFixtureBuilder.build();
    }

    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return fixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }
}
