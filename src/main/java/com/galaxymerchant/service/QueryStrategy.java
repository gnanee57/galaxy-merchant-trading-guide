package com.galaxymerchant.service;

import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;

import java.util.List;

public interface QueryStrategy {
    void processQuery(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits);
}
