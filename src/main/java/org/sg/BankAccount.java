package org.sg;

import java.time.LocalDateTime;
import java.util.List;

import static java.math.BigDecimal.valueOf;

public class BankAccount {

    private final DateTime date;
    private final Printer printer;
    private final TransactionConnector transactionConnector;


    public BankAccount(final TransactionConnector transactionConnector, final DateTime date, final Printer printer) {
        this.transactionConnector = transactionConnector;
        this.date = date;
        this.printer = printer;
    }

    public void deposit(final long value) {
        LocalDateTime localDateTime = date.getTime();
        transactionConnector.addTransaction(new Transaction(new Amount(valueOf(value,2)), localDateTime));
    }

    public void withdrawal(final long value) {
        LocalDateTime localDateTime = date.getTime();
        transactionConnector.addTransaction(new Transaction(new Amount(valueOf(-value, 2)), localDateTime));
    }

    public void printStatements() {
        printer.printStatements(transactionConnector.getTransactions());
    }

}
