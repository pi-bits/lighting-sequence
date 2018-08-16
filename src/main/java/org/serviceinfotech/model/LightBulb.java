package org.serviceinfotech.model;

public class LightBulb {
    private final Colour colour;

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    public Colour getColour() {
        return colour;
    }

    public State getState() {
        return state;
    }

    public LightBulb(Colour colour) {
        this.colour = colour;
        this.state = State.OFF;
    }


}
