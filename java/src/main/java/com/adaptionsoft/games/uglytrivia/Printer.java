package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

class Printer {
    private PrintStream printStream;


    Printer(PrintStream printStream) {
        this.printStream = printStream;
    }

    void printLine(String toPrint) {
        printStream.println(toPrint);
    }
}
