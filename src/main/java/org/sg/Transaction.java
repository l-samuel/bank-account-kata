package org.sg;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {

    private final Amount amount;

    private final LocalDateTime date;

    public Transaction(final Amount amount, final LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public Amount getAmount() {
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
        return Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + getAmount().getValue() +
                ", date=" + date +
                '}';
    }
}
