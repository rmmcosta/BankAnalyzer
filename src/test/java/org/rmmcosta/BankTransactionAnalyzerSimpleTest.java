package org.rmmcosta;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionAnalyzerSimpleTest {
    @Test
    void isTheTotalTransactionsCorrect() throws IOException {
        final Path path2TransactionsCSV = Paths.get("src/test/resources/BankTransactions.csv");
        BankTransactionAnalyzerSimple bankTransactionAnalyzerSimple = new BankTransactionAnalyzerSimple(path2TransactionsCSV);
        assertEquals(6820, bankTransactionAnalyzerSimple.getTotalTransactions());
    }
}