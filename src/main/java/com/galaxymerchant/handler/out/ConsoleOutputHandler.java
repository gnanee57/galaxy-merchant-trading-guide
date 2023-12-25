package com.galaxymerchant.handler.out;

public class ConsoleOutputHandler implements OutputHandler {
    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
    @Override
    public void write(String line) {
        System.out.print(line);
    }
}
