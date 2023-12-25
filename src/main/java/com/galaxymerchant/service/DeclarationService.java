package com.galaxymerchant.service;

import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;

import java.util.List;

public interface DeclarationService {
    void processDeclaration(String line, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits);
}
