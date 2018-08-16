package org.serviceinfotech.controller;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ColourAlgorithmControllerTest {

    public static final int NUMBER_OF_LIGHTS = 10;
    LightingFixture lightingFixture;
    @Test
    public void shouldTurnOnGroupOfRedLightsForOneSecond() throws Exception {
        lightingFixture = new LightingFixture(buildAlternativeLightBulbFixture(NUMBER_OF_LIGHTS));
        ColourAlgorithmController controller  = new ColourAlgorithmController(lightingFixture);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(NUMBER_OF_LIGHTS);
        taskExecutor.submit(controller);

        for (int i = 0; i < Colour.values().length; i++) {
            getLightBulbsByColour(Colour.values()[i]).stream().forEach(lightBulb -> {
                Assert.assertThat(lightBulb.getColour().getColour(), lightBulb.getState(), Is.is(State.ON));
            });
            Thread.sleep(1000);
            getLightBulbsByColour(Colour.values()[i]).stream().forEach(lightBulb -> {
                Assert.assertThat(lightBulb.getColour().getColour(), lightBulb.getState(), Is.is(State.OFF));
            });
        }
        taskExecutor.shutdown();
        taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }

    public static List<LightBulb> buildAlternativeLightBulbFixture(int numberOfLights) {
        List<LightBulb> lightBulbs = new ArrayList<LightBulb>(20);
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

    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return lightingFixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }
}