package org.serviceinfotech.model;

public enum State {
    ON(Boolean.TRUE),
    OFF(Boolean.FALSE);

    private Boolean value;

    State(Boolean value) {
        this.value = value;
    }
    public Boolean getValue() {
        return value;
    }
}
