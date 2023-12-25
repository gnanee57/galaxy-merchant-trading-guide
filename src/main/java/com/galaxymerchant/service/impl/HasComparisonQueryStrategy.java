package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.ComparisonType;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.parser.QueryParser;
import com.galaxymerchant.service.QueryStrategy;

import java.util.List;

public class HasComparisonQueryStrategy implements QueryStrategy {
    private final ComparisonType comparisonType;
    private final OutputHandler outputHandler;

    public HasComparisonQueryStrategy(ComparisonType comparisonType, OutputHandler outputHandler) {
        this.comparisonType = comparisonType;
        this.outputHandler = outputHandler;
    }

    @Override
    public void processQuery(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        try {
            Boolean out = QueryParser.processIntergalacticUnitsComparisonQuery(parts.subList(1, parts.indexOf(comparisonType == ComparisonType.GREATER_THAN ? "larger" : "smaller")), parts.subList(parts.indexOf("than") + 1, parts.size() - 2) , intergalacticUnits, comparisonType);
            outputHandler.write(String.join(" ", parts.subList(1, parts.indexOf(comparisonType == ComparisonType.GREATER_THAN ? "larger" : "smaller"))));
            outputHandler.write(getComparisonResultMessage(out));
            outputHandler.writeLine(String.join(" ", parts.subList(parts.indexOf("than") + 1, parts.size() - 2)));
        } catch (InvalidQueryException e) {
            outputHandler.writeLine(e.getMessage());
        }
    }

    private String getComparisonResultMessage(Boolean comparisonResult) {
        if(comparisonType == ComparisonType.GREATER_THAN) {
            return comparisonResult ? " is larger than " : " is smaller than ";
        } else {
            return comparisonResult ? " is smaller than " : " is larger than ";
        }
    }
}