package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;

public abstract class AbstractController {
    private LightingFixture fixture;

    public AbstractController(LightingFixture fixture) {
        this.fixture = fixture;
    }

    protected  void toggleLightOnOff() throws InterruptedException {
        List<LightBulb> lightBulbs = this.fixture.getLightBulbs();
        for (int i = 0; i < lightBulbs.size(); i++) {
            LightBulb lightBulb = lightBulbs.get(i);
            lightBulb.setState(State.ON);
            printMessage(i, lightBulb);
            Thread.sleep(500);
            lightBulb.setState(State.OFF);
            printMessage(i, lightBulb);
        }
    }

    private void printMessage(int lightNumber, LightBulb lightBulb) {
        System.out.println("Light " + (lightNumber + 1) + "\t" + lightBulb.getColour().name() + "\t" + lightBulb.getState().name());
    }

    protected  void changeColours() throws InterruptedException {
        //Iterate through all the available colours.
        for (int i = 0; i < Colour.values().length; i++) {
            //Switch on Light on the fixture for a Color.
            fixture.switchLightsOnByColour(Colour.values()[i]);
            //Keep it turned on for 30 seconds
            Thread.sleep(1000);
            //Keep it turned on for 30 seconds  and then switch off.
            fixture.switchLightsOffByColour(Colour.values()[i]);
        }
    }
}
