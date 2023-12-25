package com.galaxymerchant.handler.input;

import java.io.InputStream;
import java.util.Scanner;

public class FileInputHandler implements InputHandler {
    private final Scanner scanner;

    public FileInputHandler(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public String readLine() {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }


}