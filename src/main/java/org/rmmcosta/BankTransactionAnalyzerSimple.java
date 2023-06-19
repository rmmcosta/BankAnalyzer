package org.rmmcosta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    final Path path2TransactionsCSV;
    public BankTransactionAnalyzerSimple(Path path2TransactionsCSV) {
        this.path2TransactionsCSV = path2TransactionsCSV;
    }

    private static final String RESOURCES = "src/main/resources";

    public double getTotalTransactions() throws IOException {
        final List<String> transactions = Files.readAllLines(path2TransactionsCSV);
        double total = 0.0;
        for (final String currentTransaction : transactions) {
            final String[] transactionDetails = currentTransaction.split(",");
            final double currAmount = Double.parseDouble(transactionDetails[1]);
            total += currAmount;
        }
        return total;
    }
}
