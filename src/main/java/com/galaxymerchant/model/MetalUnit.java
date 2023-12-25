package com.galaxymerchant.model;

public class MetalUnit {
    private final String metalName;
    private final double value;

    public MetalUnit(String metalName, double value) {
        this.metalName = metalName;
        this.value = value;
    }

    public String getMetalName() {
        return metalName;
    }

    public double getValue() {
        return value;
    }
}
