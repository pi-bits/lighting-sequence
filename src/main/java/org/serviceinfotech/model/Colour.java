package org.serviceinfotech.model;

public enum Colour {
    RED("Red"),
    GREEN("Green"),
    WHITE("White");

    private String colour;
    Colour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
