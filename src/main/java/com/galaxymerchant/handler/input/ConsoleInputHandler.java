package com.galaxymerchant.handler.input;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}
