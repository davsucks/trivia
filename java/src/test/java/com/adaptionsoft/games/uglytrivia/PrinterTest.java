package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrinterTest {

    private PrintStream mockedPrintStream;
    private Printer printer;

    @Before
    public void setup() {
        mockedPrintStream = mock(PrintStream.class);
        printer = new Printer(mockedPrintStream);
    }

    @Test
    public void shouldWriteToPrintStream() {
       printer.printLine("This is what I want to print");

       verify(mockedPrintStream).println("This is what I want to print");
    }
}
