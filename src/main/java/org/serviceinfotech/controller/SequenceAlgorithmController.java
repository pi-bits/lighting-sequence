package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

public class SequenceAlgorithmController extends AbstractController implements Controller {
    public SequenceAlgorithmController(LightingFixture fixture) {
        super(fixture);
    }

    @Override
    public void run() {
        try {
            toggleLightOnOff();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
