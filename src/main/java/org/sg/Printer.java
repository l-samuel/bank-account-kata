package org.sg;

public class Printer {
    private final OutputConsole console;

    public Printer(final OutputConsole console) {
        this.console = console;
    }

    public void printStatements() {
        console.print("DATE | AMOUNT | BALANCE");
    }
}
