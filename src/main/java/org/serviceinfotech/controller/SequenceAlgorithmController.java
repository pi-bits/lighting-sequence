package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

public class SequenceAlgorithmController extends AbstractController implements Controller {
    public static final long LIGHTING_SEQ_TIMER = 500l;

    public SequenceAlgorithmController(LightingFixture fixture) {
        super(fixture);
    }

    @Override
    public void run() {
        try {
            toggleLightOnOff(LIGHTING_SEQ_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
