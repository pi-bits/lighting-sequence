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
    LightingFixture lightingFixture = new LightingFixture(buildAlternativeLightBulbFixture());


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
        Assert.assertThat(lightBulbs.get(7).getState(), Is.is(State.ON));

    }

    @Test
    public void shouldTurnOffLightBulbsByColour() throws Exception {
        lightingFixture.switchLightsOffByColour(Colour.GREEN);
        List<LightBulb> lightBulbs = lightingFixture.getLightBulbs();
        Assert.assertThat(lightBulbs.get(1).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(4).getState(), Is.is(State.OFF));
        Assert.assertThat(lightBulbs.get(5).getState(), Is.is(State.OFF));

    }

    private List<LightBulb> buildAlternativeLightBulbFixture() {

        List<LightBulb> lightBulbs = new ArrayList<LightBulb>(6);
        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));

        lightBulbs.add(new LightBulb(Colour.RED));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.GREEN));
        lightBulbs.add(new LightBulb(Colour.WHITE));
        lightBulbs.add(new LightBulb(Colour.RED));
        return lightBulbs;

    }

    private List<LightBulb> getLightBulbsByColour(Colour colour) {
        return lightingFixture.getLightBulbs().stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }

}