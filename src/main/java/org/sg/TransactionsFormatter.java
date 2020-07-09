package org.sg;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionsFormatter {

    private static final String SEPARATOR = "|";

    public List<String> format(final List<Transaction> transactions) {
        sortTransactionsByDate(transactions);
        List<String> transactionsFormatted = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            String date = transactions.get(i).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String amount = String.valueOf(transactions.get(i).getAmount().getValue());
            BigDecimal balance = computeBalance(transactions.subList(i, transactions.size()));
            transactionsFormatted.add(formatTransaction(date, amount, balance));
        }
        return transactionsFormatted;
    }

    private void sortTransactionsByDate(final List<Transaction> transactions) {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
    }

    private BigDecimal computeBalance(final List<Transaction> transactions) {
        return transactions.stream().map(transaction -> transaction.getAmount().getValue()).reduce(BigDecimal.valueOf(0, 2), BigDecimal::add);
    }

    private String formatTransaction(final String date, final String amount, final BigDecimal balance) {
        return date + SEPARATOR + amount + SEPARATOR + balance;
    }
}