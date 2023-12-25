package com.galaxymerchant.service;

import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;

import java.util.List;

public interface QueryService {
    void processQuery(String line, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits);
}
