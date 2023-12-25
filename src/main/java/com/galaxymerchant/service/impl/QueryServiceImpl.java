package com.galaxymerchant.service.impl;

import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.ComparisonType;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.model.QueryType;
import com.galaxymerchant.service.QueryService;
import com.galaxymerchant.service.QueryStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryServiceImpl implements QueryService {
    private final Map<QueryType, QueryStrategy> strategies;
    private final OutputHandler outputHandler;

    public QueryServiceImpl(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        strategies = new HashMap<>();
        strategies.put(QueryType.HOW_MUCH_IS, new HowMuchIsQueryStrategy(outputHandler));
        strategies.put(QueryType.HOW_MANY_CREDITS_IS, new HowManyCreditsIsQueryStrategy(outputHandler));
        strategies.put(QueryType.HAS_MORE_CREDITS_THAN, new HasCreditsComparisonQueryStrategy(ComparisonType.GREATER_THAN, outputHandler));
        strategies.put(QueryType.HAS_LESS_CREDITS_THAN, new HasCreditsComparisonQueryStrategy(ComparisonType.LESS_THAN, outputHandler));
        strategies.put(QueryType.LARGER_THAN, new HasComparisonQueryStrategy(ComparisonType.GREATER_THAN, outputHandler));
        strategies.put(QueryType.SMALLER_THAN, new HasComparisonQueryStrategy(ComparisonType.LESS_THAN, outputHandler));
    }
    @Override
    public void processQuery(String line, List<IntergalacticUnit> intergalacticUnits, List<MetalUnit> metalUnits) {
        line = line.replace("?", " ?").replace("Is", "Is ");
        List<String> parts = Arrays.asList(line.split("\\s+"));
        QueryType queryType = QueryType.fromString(line);
        if (queryType != null && strategies.containsKey(queryType)) {
            strategies.get(queryType).processQuery(parts, intergalacticUnits, metalUnits);
        } else {
            outputHandler.writeLine("I have no idea what you are talking about");
        }
    }
}
