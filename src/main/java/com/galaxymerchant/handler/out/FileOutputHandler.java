package com.galaxymerchant.handler.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOutputHandler implements OutputHandler {
    private final PrintWriter writer;

    public FileOutputHandler(String filePath) throws FileNotFoundException {
        writer = new PrintWriter(new File(filePath));
    }

    @Override
    public void writeLine(String line) {
        writer.println(line);
    }

    @Override
    public void write(String line) {
        writer.print(line);
    }
}
