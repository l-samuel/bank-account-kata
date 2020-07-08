package org.sg.transaction;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private long amount;

    private LocalDateTime date;

    public Transaction(final long amount, final LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
