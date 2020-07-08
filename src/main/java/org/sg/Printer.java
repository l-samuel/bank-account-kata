package org.sg;

import org.sg.transaction.Transaction;

import java.util.List;

public class Printer {
    private  static final String HEADER = "DATE | AMOUNT | BALANCE";
    private final OutputConsole console;
    private final TransactionFormatter transactionFormatter;

    public Printer(final OutputConsole console, final TransactionFormatter transactionFormatter) {
        this.console = console;
        this.transactionFormatter = transactionFormatter;
    }

    public void printStatements(final List<Transaction> transactions) {
        List<String> statementsToPrint = transactionFormatter.formatTransactions(transactions);
        console.print(HEADER);
        statementsToPrint.forEach(s -> console.print(s));
    }


}
