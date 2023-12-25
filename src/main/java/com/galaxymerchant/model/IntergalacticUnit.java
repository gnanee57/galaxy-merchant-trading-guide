package com.galaxymerchant.model;

public class IntergalacticUnit {
    private final String unitName;
    private final String romanEquivalent;

    public IntergalacticUnit(String unitName, String romanEquivalent) {
        this.unitName = unitName;
        this.romanEquivalent = romanEquivalent;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getRomanEquivalent() {
        return romanEquivalent;
    }
}
