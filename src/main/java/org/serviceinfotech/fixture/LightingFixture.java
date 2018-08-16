package org.serviceinfotech.fixture;

import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;


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

        for (int i = 0; i < lightBulbs.size(); i++) {
            LightBulb lightBulb = lightBulbs.get(i);
            if (lightBulb.getColour().equals(colour)) {
                lightBulb.setState(state);
                printMessage(i, lightBulb);
            }
        }

    }

    private static void printMessage(int lightNumber, LightBulb lightBulb) {
        System.out.println("Light " + (lightNumber + 1) + "\t" + lightBulb.getColour().name() + "\t" + lightBulb.getState().name());
    }
}
