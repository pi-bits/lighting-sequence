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

    /**
     * Switches Light bulbs to ON State in the Lighting Fixture
     * @param colour
     */
    public void switchLightsOnByColour(Colour colour) {
        toggleLightSwitch(colour, State.ON);
    }

    /**
     * Switches Light bulbs to OFF State in the Lighting Fixture
     * @param colour
     */
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

    public static void printMessage(int lightNumber, LightBulb lightBulb) {
        System.out.println("Light " + (lightNumber + 1) + "\t" + lightBulb.getColour().name() + "\t" + lightBulb.getState().name());
    }
}
