package org.serviceinfotech.model;

public enum Colour {
    RED("RED"),
    GREEN("GREEN"),
    WHITE("WHITE");

    private String colour;
    Colour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
