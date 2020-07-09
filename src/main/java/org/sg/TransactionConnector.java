package org.sg;

import java.util.List;

public interface TransactionConnector {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
}
