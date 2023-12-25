package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.ComparisonType;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.parser.QueryParser;
import com.galaxymerchant.service.QueryStrategy;

import java.util.List;

public class HasCreditsComparisonQueryStrategy implements QueryStrategy {
    private final ComparisonType comparisonType;
    private final OutputHandler outputHandler;

    public HasCreditsComparisonQueryStrategy(ComparisonType comparisonType, OutputHandler outputHandler) {
        this.comparisonType = comparisonType;
        this.outputHandler = outputHandler;
    }

    @Override
    public void processQuery(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        try {
            Boolean out = QueryParser.processCreditComparisonQuery(parts.subList(1, parts.indexOf("has")), parts.subList(parts.indexOf("than") + 1, parts.size() - 1), intergalacticUnits, metalUnits, comparisonType);
            outputHandler.write(String.join(" ", parts.subList(1, parts.indexOf("has"))));
            outputHandler.write(getComparisonResultMessage(out));
            outputHandler.writeLine(String.join(" ", parts.subList(parts.indexOf("than") + 1, parts.size() - 1)));
        } catch (IllegalArgumentException e) {
            throw new InvalidQueryException(e.getMessage());
        } catch (InvalidQueryException e) {
            outputHandler.writeLine(e.getMessage());
        }
    }

    private String getComparisonResultMessage(Boolean comparisonResult) {
        if(comparisonType == ComparisonType.GREATER_THAN) {
            return comparisonResult ? " has more Credits than " : " has less Credits than ";
        } else {
            return comparisonResult ? " has less Credits than " : " has more Credits than ";
        }
    }
}
