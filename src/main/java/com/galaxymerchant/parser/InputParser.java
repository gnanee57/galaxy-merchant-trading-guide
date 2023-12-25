package com.galaxymerchant.parser;

import com.galaxymerchant.handler.input.InputHandler;
import com.galaxymerchant.handler.out.CommandPrinter;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.model.IntergalacticUnit;
import com.galaxymerchant.model.MetalUnit;
import com.galaxymerchant.service.DeclarationService;
import com.galaxymerchant.service.QueryService;

import java.util.*;

public class InputParser {
    private final List<IntergalacticUnit> intergalacticUnits = new ArrayList<>();
    private final List<MetalUnit> metalUnits = new ArrayList<>();
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final DeclarationService declarationService;
    private final QueryService queryService;
    private final CommandPrinter commandPrinter;

    public InputParser(InputHandler inputHandler, OutputHandler outputHandler, CommandPrinter commandPrinter, DeclarationService declarationService, QueryService queryService) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.declarationService = declarationService;
        this.queryService = queryService;
        this.commandPrinter = commandPrinter;
    }

    public void processInput() {
        try {
            String line;
            while (inputHandler.hasNextLine() && (line = inputHandler.readLine()) != null) {
                String lowerCaseLine = line.toLowerCase();
                if (lowerCaseLine.contains("?")) {
                    try {
                        queryService.processQuery(line, intergalacticUnits, metalUnits);
                    } catch (Exception e) {
                        outputHandler.writeLine("Error occurred while processing input: " + e.getMessage());
                    }
                    continue;
                }
                switch (lowerCaseLine) {
                    case "exit":
                        commandPrinter.printExitMessage();
                        break;
                    case "reset":
                        intergalacticUnits.clear();
                        metalUnits.clear();
                        commandPrinter.printResetMessage();
                        break;
                    case "show":
                        outputHandler.writeLine("Intergalactic Units: ");
                        intergalacticUnits.forEach(intergalacticUnit -> System.out.println(intergalacticUnit.getUnitName() + " " + intergalacticUnit.getRomanEquivalent()));
                        outputHandler.writeLine("Metal Units: ");
                        metalUnits.forEach(metalUnit -> System.out.println(metalUnit.getMetalName() + " " + metalUnit.getValue()));
                        break;
                    case "help":
                        commandPrinter.printCommands();
                        break;
                    default:
                        try {
                            declarationService.processDeclaration(line, intergalacticUnits, metalUnits);
                        } catch (Exception e) {
                            outputHandler.writeLine("Error occurred while processing input: " + e.getMessage());
                        }
                        break;
                }
            }
        } catch (Exception e) {
            outputHandler.writeLine("Error occurred while processing input: " + e.getMessage());
        } finally {
            commandPrinter.printExitMessage();
        }
    }
}
