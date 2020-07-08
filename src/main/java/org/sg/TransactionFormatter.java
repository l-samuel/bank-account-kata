package org.sg;

import org.sg.transaction.Transaction;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionFormatter {

    private static final String SEPARATOR = "|";

    public List<String> formatTransactions(final List<Transaction> transactions) {
        sortTransactionsByDate(transactions);
        List<String> transactionsFormatted = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            String date = transactions.get(i).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String amount = String.valueOf(transactions.get(i).getAmount());
            long balance = computeBalance(transactions.subList(i, transactions.size()));
            transactionsFormatted.add(format(date, amount, balance));
        }
        return transactionsFormatted;
    }


    private void sortTransactionsByDate(final List<Transaction> transactions) {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
    }

    private long computeBalance(final List<Transaction> transactions) {
        return transactions.stream().map(Transaction::getAmount).reduce(0L, Long::sum);
    }

    private String format(final String date, final String amount, final long balance) {
        return date + SEPARATOR + new BigDecimal(amount).movePointLeft(2) + SEPARATOR + new BigDecimal(balance).movePointLeft(2);
    }
}