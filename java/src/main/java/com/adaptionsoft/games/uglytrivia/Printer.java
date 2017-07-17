package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Printer {
    private PrintStream printStream;


    public Printer(PrintStream printStream) {
        this.printStream = printStream;
    }

    void printLine(String toPrint) {
        printStream.println(toPrint);
    }
}
