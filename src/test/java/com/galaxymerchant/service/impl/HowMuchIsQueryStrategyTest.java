package com.galaxymerchant.service.impl;

import com.galaxymerchant.exception.InvalidQueryException;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class HowMuchIsQueryStrategyTest {
    private OutputHandler outputHandler;
    private HowMuchIsQueryStrategy strategy;

    @Before
    public void setUp() {
        outputHandler = mock(OutputHandler.class);
        strategy = new HowMuchIsQueryStrategy(outputHandler);
    }

    @Test
    public void testProcessQuery() {
        List<String> parts = Arrays.asList("how", "much", "is", "pish", "tegj", "glob", "glob", "?");
        List<IntergalacticUnit> intergalacticUnits = Arrays.asList(new IntergalacticUnit("glob", "I"), new IntergalacticUnit("pish", "X"), new IntergalacticUnit("tegj", "L"));
        List<MetalUnit> metalUnits = Arrays.asList(new MetalUnit("Silver", 17), new MetalUnit("Gold", 14450), new MetalUnit("Iron", 195.5));
        strategy.processQuery(parts, intergalacticUnits, metalUnits);

        verify(outputHandler).writeLine("pish tegj glob glob is 42");
    }

    @Test(expected = InvalidQueryException.class)
    public void testProcessQueryThrowsException() {
        List<String> parts = Arrays.asList("how", "much", "is", "?");
        List<IntergalacticUnit> intergalacticUnits = List.of(new IntergalacticUnit("glob", "I"));
        List<MetalUnit> metalUnits = List.of(new MetalUnit("Silver", 17));

        strategy.processQuery(parts, intergalacticUnits, metalUnits);
    }
}
