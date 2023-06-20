package org.rmmcosta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rmmcosta.domain.BankTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTransactionTest {
    BankTransaction bankTransaction1;
    BankTransaction bankTransaction2;

    @BeforeEach
    void setUp() {
        bankTransaction1 = new BankTransaction(LocalDate.of(2020, 1, 1), 100, "entity 1");
        bankTransaction2 = new BankTransaction(LocalDate.of(2020, 3, 1), -100, "entity 2");
    }

    @Test
    void testTransactionComparisonBasedOnAmount() {
        assertEquals(0, bankTransaction1.compareTo(bankTransaction1));
        assertEquals(1, bankTransaction1.compareTo(bankTransaction2));
        assertEquals(-1, bankTransaction2.compareTo(bankTransaction1));
    }

    @Test
    void testTransactionToString() {
        assertEquals("BankTransaction[date=2020-01-01,amount=100.0,entity=entity 1]", bankTransaction1.toString());
    }

    @Test
    void testDateOk() {
        assertEquals(LocalDate.of(2020, 1, 1), bankTransaction1.date());
    }

    @Test
    void testAmountOk() {
        assertEquals(100, bankTransaction1.amount());
    }

    @Test
    void testEntityOk() {
        assertEquals("entity 1", bankTransaction1.entity());
    }

    @Test
    void testPrintListTransactions() {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        bankTransactions.add(bankTransaction1);
        bankTransactions.add(bankTransaction2);
        assertEquals("BankTransaction[date=2020-01-01,amount=100.0,entity=entity1]BankTransaction[date=2020-03-01,amount=-100.0,entity=entity2]",
                BankTransaction.getListTransactionsToPrint(bankTransactions)
                        .replaceAll("\\s", ""));
    }
}