package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;

import static org.serviceinfotech.fixture.LightingFixture.printMessage;

public abstract class AbstractController {
    private LightingFixture fixture;

    public AbstractController(LightingFixture fixture) {
        this.fixture = fixture;
    }

    /**
     * Switch ON/OFF lights in Order of the fixture length from first to last.
     * @throws InterruptedException
     * @param timer
     */
    protected  void toggleLightOnOff(long timer) throws InterruptedException {
        List<LightBulb> lightBulbs = this.fixture.getLightBulbs();
        for (int i = 0; i < lightBulbs.size(); i++) {
            LightBulb lightBulb = lightBulbs.get(i);
            lightBulb.setState(State.ON);
            printMessage(i, lightBulb);
            Thread.sleep(timer);
            lightBulb.setState(State.OFF);
            printMessage(i, lightBulb);
        }
    }


    /**
     * Switch ON/OFF a Group of lights of same colour in the fixture.
     * @throws InterruptedException
     * @param timer
     */
    protected  void changeColours(long timer) throws InterruptedException {
        for (int i = 0; i < Colour.values().length; i++) {
            fixture.switchLightsOnByColour(Colour.values()[i]);
            Thread.sleep(timer);
            fixture.switchLightsOffByColour(Colour.values()[i]);
        }
    }
}
