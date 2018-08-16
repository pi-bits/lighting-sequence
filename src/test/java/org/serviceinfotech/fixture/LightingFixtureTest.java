package org.serviceinfotech.fixture;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LightingFixtureTest {
    LightingFixture lightingFixture = new LightingFixtureBuilder(10,Colour.RED,Colour.GREEN,Colour.WHITE).build();


    @Test
    public void shouldGetLightBulbsByColour() throws Exception {
        List<LightBulb> redLightBulbs = getLightBulbsByColour(Colour.RED);
        redLightBulbs.forEach(lightBulb -> Assert.assertThat(lightBulb.getColour(), Is.is(Colour.RED)));

    }


    @Test
    public void shouldTurnOnLightBulbsByColour() throws Exception {
        lightingFixture.switchLightsOnByColour(Colour.RED);
        List<LightBulb> lightBulbs = lightingFixture.getLightBulbs();
        Assert.assertThat(lightBulbs.get(0).getState(), Is.is(State.ON));
        Assert.assertThat(lightBulbs.get(3).getState(), Is.is(State.ON));
        Assert.assertThat(lightBulbs.get(7).getColour(), Is.is(Colour.GREEN));
        Assert.assertThat(lightBulbs.get(7).getState(), Is.is(State.OFF));

    }

    @Test
    public void shouldTurnOffLightBulbsByColour() throws Exception {
        lightingFixture.switchLightsOffByColour(Colour.GREEN);
        List<LightBulb> lightBulbs = lightingFixture.getLightBulbs();
        Assert.assertThat(lightBulbs.get(1).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(4).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(5).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(3).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(3).getColour(), Is.is(Colour.RED));

    }

    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return lightingFixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }

}