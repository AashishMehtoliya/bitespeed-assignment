package com.bitespeed.assignment;

public enum LinkPrecedence {
    PRIMARY("primary"),
    SECONDARY("secondary");

    private final String secondary;
    LinkPrecedence(String secondary) {
        this.secondary = secondary;
    }

    public String getName() {
        return secondary;
    }
}
