package com.galaxymerchant.parser;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.model.ComparisonType;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.util.RomanNumeralUtils;

import java.util.List;

public class QueryParser {
    public static int processIntergalacticUnitsQuery(List<String> queryItems, List<IntergalacticUnit> units) {
        if(queryItems.isEmpty()) {
            throw new InvalidQueryException("Requested comparison is not invalid format");
        }
        StringBuilder intergalacticUnits = new StringBuilder();
        queryItems.forEach(queryItem -> units.stream()
                .filter(unit -> unit.getUnitName().equalsIgnoreCase(queryItem))
                .findFirst()
                .ifPresent(unit -> intergalacticUnits.append(unit.getRomanEquivalent())));

        if (!intergalacticUnits.isEmpty()) {
            try {
                return RomanNumeralUtils.romanToNumber(intergalacticUnits.toString());
            } catch (IllegalArgumentException e) {
                throw new InvalidQueryException("Requested comparison is not invalid format");
            }
        }
        return -1;
    }

    public static double processCreditConversionQuery(List<String> interGalacticUnits, String metalUnit, List<IntergalacticUnit> units,
                                                    List<MetalUnit> metalUnits) {

        int decimalValue = processIntergalacticUnitsQuery(interGalacticUnits, units);
        if(decimalValue == -1) {
            throw new InvalidQueryException("Requested comparison is invalid format");
        } else {
            double creditsPerUnit = metalUnits.stream()
                    .filter(metal -> metal.getMetalName().equalsIgnoreCase(metalUnit))
                    .findFirst()
                    .map(MetalUnit::getValue)
                    .orElse(-1.0);

            if (creditsPerUnit == -1.0) {
                throw new InvalidQueryException("Requested comparison is invalid format");
            } else {
                return decimalValue * creditsPerUnit;
            }
        }
    }

    public static Boolean processCreditComparisonQuery(List<String> compare1, List<String> compare2, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits, ComparisonType comparisonType) {
        double compare1Value = processCreditConversionQuery(compare1.subList(0, compare1.size() - 1), compare1.get(compare1.size() - 1), intergalacticUnits, metalUnits);
        double compare2Value = processCreditConversionQuery(compare2.subList(0, compare2.size() - 1), compare2.get(compare2.size() - 1), intergalacticUnits, metalUnits);

        if(compare1Value == -1 || compare2Value == -1) {
            throw new InvalidQueryException("Requested comparison is not invalid format");
        } else {
            return compareByType(comparisonType, compare1Value, compare2Value);
        }
    }

    public static Boolean processIntergalacticUnitsComparisonQuery(List<String> compare1, List<String> compare2, List<IntergalacticUnit> intergalacticUnits, ComparisonType comparisonType) {
        int compare1Value = processIntergalacticUnitsQuery(compare1, intergalacticUnits);
        int compare2Value = processIntergalacticUnitsQuery(compare2, intergalacticUnits);

        if(compare1Value == -1 || compare2Value == -1) {
            throw new InvalidQueryException("Requested comparison is not invalid format");
        } else {
            return compareByType(comparisonType, compare1Value, compare2Value);
        }
    }

    private static boolean compareByType(ComparisonType comparisonType, Integer compare1Value, Integer compare2Value) {
        if(comparisonType == ComparisonType.GREATER_THAN) {
            return compare1Value > compare2Value;
        } else if (comparisonType == ComparisonType.LESS_THAN) {
            return compare1Value < compare2Value;
        } else {
            return compare1Value.equals(compare2Value);
        }
    }

    private static boolean compareByType(ComparisonType comparisonType, Double compare1Value, Double compare2Value) {
        final double EPSILON = 1E-10;
        if(comparisonType == ComparisonType.GREATER_THAN) {
            return compare1Value > compare2Value;
        } else if (comparisonType == ComparisonType.LESS_THAN) {
            return compare1Value < compare2Value;
        } else {
            return Math.abs(compare1Value - compare2Value) < EPSILON;
        }
    }
}
