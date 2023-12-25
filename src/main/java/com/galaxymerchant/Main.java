package com.galaxymerchant;

import com.galaxymerchant.handler.input.ConsoleInputHandler;
import com.galaxymerchant.handler.input.FileInputHandler;
import com.galaxymerchant.handler.input.InputHandler;
import com.galaxymerchant.handler.out.CommandPrinter;
import com.galaxymerchant.handler.out.ConsoleOutputHandler;
import com.galaxymerchant.handler.out.OutputHandler;
import com.galaxymerchant.parser.InputParser;
import com.galaxymerchant.service.impl.DeclarationServiceImpl;
import com.galaxymerchant.service.impl.QueryServiceImpl;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        CommandPrinter commandPrinter = new CommandPrinter(outputHandler);

        outputHandler.writeLine("Do you want to take input from file? (y/n)");
        String input = inputHandler.readLine();

        if(input.equals("y")) {
            ClassLoader classLoader = Main.class.getClassLoader();
            try {
                inputHandler = new FileInputHandler(Objects.requireNonNull(classLoader.getResourceAsStream("input.txt")));
            } catch (Exception e) {
                System.out.println("File not found. Using Console Input.");
                commandPrinter.printWelcomeMessage();
            }
        } else if(input.equals("n")) {
            commandPrinter.printWelcomeMessage();
        } else {
            System.out.println("Invalid input. Using Console Input.");
            commandPrinter.printWelcomeMessage();
        }

        InputParser inputParser = new InputParser(inputHandler, outputHandler, commandPrinter, new DeclarationServiceImpl(outputHandler), new QueryServiceImpl(outputHandler));
        inputParser.processInput();
    }
}