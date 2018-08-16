package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;
import org.serviceinfotech.model.LightBulb;
import org.serviceinfotech.model.State;

import java.util.List;

public class SequenceAlgorithmController implements Controller {
    private LightingFixture fixture;
    public SequenceAlgorithmController(LightingFixture fixture) {
        this.fixture = fixture;
    }

    @Override
    public void run() {
        List<LightBulb> lightBulbs = this.fixture.getLightBulbs();
        for (int i = 0; i < lightBulbs.size(); i++) {
            LightBulb lightBulb = lightBulbs.get(i);
            lightBulb.setState(State.ON);
            System.out.println("Light " + (i + 1) + "\t" + lightBulb.getColour().name() + "\t" + lightBulb.getState().name());
            try {
                Thread.sleep(500);
                lightBulb.setState(State.OFF);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Light " + (i + 1) + "\t" + lightBulb.getColour().name() + "\t" + lightBulb.getState().name());
        }
    }
}
