package org.serviceinfotech.controller;

import org.hamcrest.core.Is;
import org.junit.Assert;
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

public class ColourAlgorithmControllerTest {

    public static final int NUMBER_OF_LIGHTS = 10;

    @Test
    public void shouldTurnOnGroupOfRedLightsForOneSecond() throws Exception {
        LightingFixture lightingFixture = new LightingFixture(buildAlternativeLightBulbFixture(NUMBER_OF_LIGHTS));
        ColourAlgorithmController controller  = new ColourAlgorithmController(lightingFixture);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(NUMBER_OF_LIGHTS);
        taskExecutor.submit(controller);

        lightingFixture.getLightBulbs().stream()
                .filter(lightBulb -> lightBulb.getColour().equals(Colour.valueOf("RED")))
                .forEach(lightBulb -> {

                    Assert.assertThat(lightBulb.getState(), Is.is(State.ON));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Assert.assertThat(lightBulb.getState(), Is.is(State.OFF));
                });
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

}