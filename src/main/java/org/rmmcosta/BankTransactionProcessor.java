package org.rmmcosta;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.rmmcosta.FileHandle.getFileLines;

public class BankTransactionProcessor {

    public static Set<Transaction> processBankTransactions(String[] bankTransactions) {
        Set<Transaction> orderedTransactions = new TreeSet<>();
        Arrays.stream(bankTransactions).forEach(line -> {
            String[] lineSplit = line.split(",");
            String date = lineSplit[0].trim();
            double amount = Double.parseDouble(lineSplit[1].trim());
            String entity = lineSplit[2].trim();
            orderedTransactions.add(new Transaction(date, amount, entity));
        });
        return orderedTransactions;
    }

    public static Set<Transaction> processBankTransactionsFile(String filePath) {
        return processBankTransactions(getFileLines(filePath));
    }


}
