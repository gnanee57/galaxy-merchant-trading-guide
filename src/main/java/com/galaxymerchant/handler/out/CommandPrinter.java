package com.galaxymerchant.handler.out;

public class CommandPrinter {
    private final OutputHandler outputHandler;

    public CommandPrinter(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public void printWelcomeMessage() {
        outputHandler.writeLine("Welcome to Galaxy Merchant Trading Guide");
        outputHandler.writeLine("========================================");
        printCommands();
        outputHandler.writeLine("========================================");
    }

    public void printCommands() {
        outputHandler.writeLine("Commands:");
        outputHandler.writeLine("1. To exit, type 'exit'");
        outputHandler.writeLine("2. To reset, type 'reset'");
        outputHandler.writeLine("3. To declare intergalactic units, type '<unit> is <roman number>'");
        outputHandler.writeLine("4. To declare metal units, type '<unit> <unit> ... <unit> <metal> is <value> Credits'");
        outputHandler.writeLine("5. To get the value of intergalactic units, type 'how much is <unit> ... <unit> ?'");
        outputHandler.writeLine("6. To get the value of metal units, type 'how many Credits is <unit> ... <unit> <metal> ?'");
        outputHandler.writeLine("7. To compare the value of metal units, type 'Does <unit> ... <unit> <metal> has more/less Credits than <unit> ... <unit> <metal> ?'");
        outputHandler.writeLine("8. To compare the value of intergalactic units, 'Is '<unit> <unit> ... <unit> larger/smaller than <unit> <unit> ... <unit> ?'");
        outputHandler.writeLine("9. To show the current input, type 'show'");
        outputHandler.writeLine("10. To show the commands, type 'help'");
    }

    public void printInvalidCommandMessage() {
        outputHandler.writeLine("Invalid command. Please try again.");
    }

    public void printExitMessage() {
        outputHandler.writeLine("Exiting program...");
    }

    public void printResetMessage() {
        outputHandler.writeLine("Resetting input...");
    }
}