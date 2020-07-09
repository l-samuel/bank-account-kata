package org.sg;

import java.util.List;

public class Printer {
    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    private final Writer console;
    private final TransactionsFormatter transactionsFormatter;

    public Printer(final Writer console, final TransactionsFormatter transactionsFormatter) {
        this.console = console;
        this.transactionsFormatter = transactionsFormatter;
    }

    public void printStatements(final List<Transaction> transactions) {
        List<String> statementsToPrint = transactionsFormatter.format(transactions);
        console.print(HEADER);
        statementsToPrint.forEach(s -> console.print(s));
    }
}
