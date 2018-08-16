package org.serviceinfotech.controller;

import org.serviceinfotech.fixture.LightingFixture;

import static org.serviceinfotech.controller.ColourAlgorithmController.COLOUR_SEQ_TIMER;
import static org.serviceinfotech.controller.SequenceAlgorithmController.LIGHTING_SEQ_TIMER;

public class AlternateAlgorithmController extends AbstractController implements Controller {
    private final int loopCounter;

    public AlternateAlgorithmController(LightingFixture fixture, int loopCounter) {
        super(fixture);
        this.loopCounter = loopCounter;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < loopCounter; i++) {
                System.out.println("Loop Number:" + i + 1);
                toggleLightOnOff(LIGHTING_SEQ_TIMER);
                changeColours(COLOUR_SEQ_TIMER);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
