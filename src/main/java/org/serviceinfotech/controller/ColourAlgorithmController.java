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
        for (int i = 0; i < Colour.values().length; i++) {
            fixture.switchLightsOnByColour(Colour.values()[i]);
            try {
                Thread.sleep(1000);
                fixture.switchLightsOffByColour(Colour.values()[i]);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
