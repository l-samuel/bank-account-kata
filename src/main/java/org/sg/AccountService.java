package org.sg;

import org.sg.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class AccountService implements BankAccount {

    private final DateTime date;
    private final List<Transaction> transactions;
    private final Printer printer;


    public AccountService(final List<Transaction> transactions, final DateTime date, final Printer printer) {
        this.transactions = transactions;
        this.date = date;
        this.printer = printer;
    }

    @Override
    public void deposit(final long amount) {
        LocalDateTime localDateTime = date.getTime();
        transactions.add(new Transaction(amount, localDateTime));
    }

    @Override
    public void withdrawal(final long amount) {
        LocalDateTime localDateTime = date.getTime();
        transactions.add(new Transaction(-amount, localDateTime));
    }

    public void printStatements() {
        printer.printStatements(transactions);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
