package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.parser.QueryParser;
import com.galaxymerchant.service.QueryStrategy;

import java.util.List;

public class HowManyCreditsIsQueryStrategy implements QueryStrategy {
    private final OutputHandler outputHandler;

    public HowManyCreditsIsQueryStrategy(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    @Override
    public void processQuery(List<String> parts, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        try {
            double out = QueryParser.processCreditConversionQuery(parts.subList(parts.indexOf("is") + 1, parts.size() - 2), parts.get(parts.size() - 2) , intergalacticUnits, metalUnits);
            out = (out == Math.floor(out)) ? ((int) out) : out;
            outputHandler.write(String.join(" ", parts.subList(parts.indexOf("is") + 1, parts.size() - 1)) + " is ");
            if (out == Math.floor(out)) {
                outputHandler.write("" +  (int) out);
            } else {
                outputHandler.write("" + out);
            }
            outputHandler.writeLine(" Credits");
        } catch (IllegalArgumentException e) {
            throw new InvalidQueryException("I have no idea what you are talking about");
        } catch (InvalidQueryException e) {
            outputHandler.writeLine(e.getMessage());
        }
    }
}
