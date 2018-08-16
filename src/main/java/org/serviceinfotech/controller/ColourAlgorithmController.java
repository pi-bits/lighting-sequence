package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

public class ColourAlgorithmController extends AbstractController implements Controller {
    public static final long COLOUR_SEQ_TIMER = 1000L;

    public ColourAlgorithmController(LightingFixture fixture) {
        super(fixture);
    }

    @Override
    public void run() {
        try {
            changeColours(COLOUR_SEQ_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
