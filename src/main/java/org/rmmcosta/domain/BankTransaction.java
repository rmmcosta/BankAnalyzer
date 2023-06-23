package org.rmmcosta.domain;

import java.time.LocalDate;
import java.util.List;

public record BankTransaction(LocalDate date, double amount, String entity) implements Comparable<BankTransaction> {
    @Override
    public int compareTo(BankTransaction o) {
        return Double.compare(amount, o.amount);
    }

    @Override
    public String toString() {
        return "BankTransaction[date=" + date + ",amount=" + amount + ",entity=" + entity + "]";
    }

    public static String getListTransactionsToPrint(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream().map(BankTransaction::toString).reduce((a, b) -> a + "\n" + b).orElse("");
    }
}
