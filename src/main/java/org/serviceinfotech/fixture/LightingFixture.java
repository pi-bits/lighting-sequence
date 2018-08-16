package org.serviceinfotech.fixture;

import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;
import java.util.stream.Collectors;

public class LightingFixture {

    private List<LightBulb> lightBulbs;

    public LightingFixture(List<LightBulb> lightBulbs) {
        this.lightBulbs = lightBulbs;
    }

    public List<LightBulb> getLightBulbs() {
        return lightBulbs;
    }


    public void switchLightsOnByColour(Colour colour) {
        toggleLightSwitch(colour, State.ON);
    }

    public void switchLightsOffByColour(Colour colour) {
        toggleLightSwitch(colour, State.OFF);
    }

    private void toggleLightSwitch(Colour colour, State state) {
        lightBulbs.stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).forEach(lightBulb -> {
            lightBulb.setState(state);
        });
    }

    public List<LightBulb> getLightBulbsByColour(Colour colour) {
        return lightBulbs.stream().filter(lightBulb -> lightBulb.getColour().equals(colour)).collect(Collectors.toList());
    }
}
