package org.serviceinfotech.controller;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.fixture.LightingFixtureBuilder;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ColourAlgorithmControllerTest {

    public static final int NUMBER_OF_LIGHTS = 10;
    LightingFixture lightingFixture;

    @Before
    public void setUp() throws Exception {
        lightingFixture = new LightingFixtureBuilder(NUMBER_OF_LIGHTS, Colour.RED, Colour.GREEN, Colour.WHITE).build();
    }

    @Test
    public void shouldTurnOnGroupOfRedLightsForOneSecond() throws Exception {

        ColourAlgorithmController controller = new ColourAlgorithmController(lightingFixture);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(NUMBER_OF_LIGHTS);
        taskExecutor.submit(controller);

        for (int i = 0; i < Colour.values().length; i++) {
            getLightBulbsByColour(Colour.values()[i]).stream().forEach(lightBulb -> {
                Assert.assertThat(lightBulb.getColour().getColour(), lightBulb.getState(), Is.is(State.ON));
            });
            Thread.sleep(ColourAlgorithmController.COLOUR_SEQ_TIMER);
            getLightBulbsByColour(Colour.values()[i]).stream().forEach(lightBulb -> {
                Assert.assertThat(lightBulb.getColour().getColour(), lightBulb.getState(), Is.is(State.OFF));
            });
        }
        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }


    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return lightingFixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }
}