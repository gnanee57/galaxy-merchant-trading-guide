package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidInputException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.service.DeclarationService;
import com.galaxymerchant.util.RomanNumeralUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DeclarationServiceImpl implements DeclarationService {
    private final OutputHandler outputHandler;

    public DeclarationServiceImpl(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    @Override
    public void processDeclaration(String line, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        List<String> parts = Arrays.asList(line.split("\\s+"));
        if (parts.stream().anyMatch("Credits"::equalsIgnoreCase)) {
            processMetalUnitDeclaration(parts, intergalacticUnits, metalUnits);
        } else if(parts.size() == 3) {
            processIntergalacticUnitDeclaration(parts, intergalacticUnits);
        } else {
            throw new InvalidInputException("I have no idea what you are talking about");
        }
    }

    private void processMetalUnitDeclaration(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        int isIndex = parts.indexOf("is");
        long credits = Long.parseLong(parts.get(isIndex + 1));
        String romanNumeral = getRomanNumeral(parts.subList(0, isIndex - 1), intergalacticUnits);
        double value = (double) credits / RomanNumeralUtils.romanToNumber(romanNumeral);
        metalUnits.add(new MetalUnit(parts.get(isIndex - 1), value));
    }

    private void processIntergalacticUnitDeclaration(List<String> parts, List<IntergalacticUnit> intergalacticUnits) {
        if (checkInterGalacticUnitNotPresent(parts.get(0), intergalacticUnits)) {
            IntergalacticUnit intergalacticUnit = new IntergalacticUnit(parts.get(0), parts.get(2));
            intergalacticUnits.add(intergalacticUnit);
        } else {
            throw new InvalidInputException("Intergalactic unit already exists");
        }
    }

    private boolean checkInterGalacticUnitNotPresent(String interGalacticUnit, List<IntergalacticUnit> intergalacticUnits) {
        return intergalacticUnits.stream().noneMatch(intergalacticUnit -> intergalacticUnit.getUnitName().contains(interGalacticUnit));
    }

    private String getRomanNumeral(List<String> interGalacticUnits, List<IntergalacticUnit> intergalacticUnitsList) {
        StringBuilder romanNumeral = new StringBuilder();
        for (String unit : interGalacticUnits) {
            IntergalacticUnit intergalacticUnit = getInterGalacticUnit(unit, intergalacticUnitsList);
            if (intergalacticUnit == null) {
                throw new InvalidInputException("Invalid intergalactic unit");
            } else {
                romanNumeral.append(intergalacticUnit.getRomanEquivalent());
            }
        }
        return romanNumeral.toString();
    }

    private IntergalacticUnit getInterGalacticUnit(String unit, List<IntergalacticUnit> intergalacticUnits) {
        return intergalacticUnits.stream()
                .filter(intergalacticUnit -> intergalacticUnit.getUnitName().equals(unit))
                .findFirst()
                .orElse(null);
    }
}