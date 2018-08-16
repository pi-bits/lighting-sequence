package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.Colour;

public class ColourAlgorithmController implements Controller {
    private LightingFixture fixture;

    public ColourAlgorithmController(LightingFixture fixture) {
        this.fixture = fixture;
    }

    @Override
    public void run() {
        //Iterate through all the available colours.
        for (int i = 0; i < Colour.values().length; i++) {
            //Switch on Light on the fixture for a Color.
            fixture.switchLightsOnByColour(Colour.values()[i]);
            try {
            //Keep it turned on for 30 seconds
                Thread.sleep(1000);
                //Keep it turned on for 30 seconds  and then switch off.
                fixture.switchLightsOffByColour(Colour.values()[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
