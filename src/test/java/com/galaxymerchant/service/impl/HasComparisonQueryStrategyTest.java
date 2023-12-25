package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.ComparisonType;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

public class HasComparisonQueryStrategyTest {
    @Mock
    private OutputHandler outputHandler;

    private HasCreditsComparisonQueryStrategy hasCreditsComparisonQueryStrategy;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hasCreditsComparisonQueryStrategy = new HasCreditsComparisonQueryStrategy(ComparisonType.GREATER_THAN, outputHandler);
    }

    @Test
    public void testProcessQuery() {
        List<String> parts = Arrays.asList("Does", "glob", "glob", "Gold", "has", "more", "Credits", "than", "glob", "Silver", "?");
        List<IntergalacticUnit> intergalacticUnits = List.of(new IntergalacticUnit("glob", "I"));
        List<MetalUnit> metalUnits = Arrays.asList(new MetalUnit("Gold", 10), new MetalUnit("Silver", 5));

        hasCreditsComparisonQueryStrategy.processQuery(parts, intergalacticUnits, metalUnits);

        verify(outputHandler).write("glob glob Gold");
        verify(outputHandler).write(" has more Credits than ");
        verify(outputHandler).writeLine("glob Silver");
    }

    @Test(expected = InvalidQueryException.class)
    public void testProcessQueryThrowsException() {
        List<String> parts = Arrays.asList("how", "many", "Credits", "is", "?");
        List<IntergalacticUnit> intergalacticUnits = List.of(new IntergalacticUnit("glob", "I"));
        List<MetalUnit> metalUnits = List.of(new MetalUnit("Silver", 17));

        hasCreditsComparisonQueryStrategy.processQuery(parts, intergalacticUnits, metalUnits);
    }
}