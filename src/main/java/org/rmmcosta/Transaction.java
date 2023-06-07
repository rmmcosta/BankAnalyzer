package org.rmmcosta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public record Transaction(String date, double amount, String entity) implements Comparable<Transaction> {
    @Override
    public int compareTo(Transaction o) {
        return Double.compare(amount, o.amount);
    }

    @Override
    public String toString() {
        return "Transaction[date=" + date + ",amount=" + amount + ",entity=" + entity + "]";
    }

    public static String getListTransactionsToPrint(Set<Transaction> transactions) {
        return transactions.stream().map(Transaction::toString).reduce((a, b) -> a + "\n" + b).orElse("");
    }

    public static String getTransactionMonth(String date) {
        Map<String, String> months = new HashMap<>();
        months.put("01", "January");
        months.put("02", "February");
        months.put("03", "March");
        months.put("04", "April");
        months.put("05", "May");
        months.put("06", "June");
        months.put("07", "July");
        months.put("08", "August");
        months.put("09", "September");
        months.put("10", "October");
        months.put("11", "November");
        months.put("12", "December");
        String monthNumber = date.replaceAll("^[0-9]{2}\\-", "").replaceAll("\\-[0-9]{4}$", "");
        //System.out.println("monthNumber = " + monthNumber);
        return months.get(monthNumber);
    }
}
