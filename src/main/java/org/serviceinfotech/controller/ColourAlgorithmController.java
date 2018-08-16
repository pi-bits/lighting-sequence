package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

public class ColourAlgorithmController extends AbstractController implements Controller {

    public ColourAlgorithmController(LightingFixture fixture) {
        super(fixture);
    }

    @Override
    public void run() {
        try {
            changeColours();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
