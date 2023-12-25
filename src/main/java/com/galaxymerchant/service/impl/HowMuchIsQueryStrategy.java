package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.parser.QueryParser;
import com.galaxymerchant.service.QueryStrategy;

import java.util.List;

public class HowMuchIsQueryStrategy implements QueryStrategy {
    private final OutputHandler outputHandler;

    public HowMuchIsQueryStrategy(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    @Override
    public void processQuery(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        try {
            int out = QueryParser.processIntergalacticUnitsQuery(parts.subList(parts.indexOf("is") + 1, parts.size() - 1), intergalacticUnits);
            outputHandler.writeLine(String.join(" ", parts.subList(parts.indexOf("is") + 1, parts.size() - 1)) + " is " + out);
        } catch (IllegalArgumentException e) {
            throw new InvalidQueryException("I have no idea what you are talking about");
        }
    }
}
