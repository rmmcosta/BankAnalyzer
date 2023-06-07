package org.rmmcosta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {
    private static final String NEWLINE = "\n";
    Transaction transaction1;
    Transaction transaction2;

    @BeforeEach
    void setUp() {
        transaction1 = new Transaction("01-01-2020", 100, "entity 1");
        transaction2 = new Transaction("01-03-2020", -100, "entity 2");
    }

    @Test
    void testTransactionComparisonBasedOnAmount() {
        assertEquals(0, transaction1.compareTo(transaction1));
        assertEquals(1, transaction1.compareTo(transaction2));
        assertEquals(-1, transaction2.compareTo(transaction1));
    }

    @Test
    void testTransactionToString() {
        assertEquals("Transaction[date=01-01-2020,amount=100.0,entity=entity 1]", transaction1.toString());
    }

    @Test
    void testDateOk() {
        assertEquals("01-01-2020", transaction1.date());
    }

    @Test
    void testAmountOk() {
        assertEquals(100, transaction1.amount());
    }

    @Test
    void testEntityOk() {
        assertEquals("entity 1", transaction1.entity());
    }

    @Test
    void testPrintListTransactions() {
        Set<Transaction> transactions = new TreeSet<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        assertEquals("Transaction[date=01-03-2020,amount=-100.0,entity=entity2]Transaction[date=01-01-2020,amount=100.0,entity=entity1]",
                Transaction.getListTransactionsToPrint(transactions)
                        .replaceAll("\\s", ""));
    }

    @Test
    void testTransactionMonth() {
        assertEquals("January", Transaction.getTransactionMonth(transaction1.date()));
        assertEquals("March", Transaction.getTransactionMonth(transaction2.date()));
    }
}