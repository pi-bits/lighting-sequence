package org.serviceinfotech.fixture;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;


public class LightingFixtureBuilderTest {
    public static final int FIXTURE_SIZE = 20;
    LightingFixtureBuilder lightingFixtureBuilder;

    @Test
    public void shouldBuildLightFixture() throws Exception {
        lightingFixtureBuilder = new LightingFixtureBuilder(FIXTURE_SIZE, Colour.RED, Colour.GREEN, Colour.WHITE);
        LightingFixture lightingFixture = lightingFixtureBuilder.build();

        List<LightBulb> actualLightBulbs = lightingFixture.getLightBulbs();
        for (int i = 0; i < FIXTURE_SIZE; i++) {
            assertThat(actualLightBulbs.get(i).getColour(), Is.is(actualLightBulbs.get(i).getColour()));
        }

    }

    private List<LightBulb> expectedLightBulbs() {
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
        return lightBulbs;
    }
}