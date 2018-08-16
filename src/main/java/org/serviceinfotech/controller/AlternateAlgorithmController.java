package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

public class AlternateAlgorithmController extends AbstractController implements Controller {
    private final int timesToAlternate;

    public AlternateAlgorithmController(LightingFixture fixture, int timesToAlternate) {
        super(fixture);
        this.timesToAlternate = timesToAlternate;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < timesToAlternate; i++) {
                toggleLightOnOff();
                changeColours();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
