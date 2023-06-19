package org.rmmcosta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionProcessorTest {
    private static final String NEWLINE = "\n";

    BankTransactionAnalyzer bankTransactionAnalyzer;

    @BeforeEach
    void setUp() {
        Set<Transaction> transactions;
        transactions = BankTransactionProcessor.processBankTransactionsFile("src\\test\\resources\\TestBankTransactions.csv");
        bankTransactionAnalyzer = new BankTransactionAnalyzer(transactions, "src\\test\\resources\\TestCategories.csv");
    }

    @Test
    void testBankTransactionsCount() {
        assertEquals(2, bankTransactionAnalyzer.getBankTransactionsCount());
    }

    @Test
    void testTotalProfitAndLoss() {
        assertEquals(5900, bankTransactionAnalyzer.getTotalProfitAndLoss());
    }

    @Test
    void testTop10Expenses() {
        Set<Transaction> top10Expenses1 = bankTransactionAnalyzer.getTop10Expenses();
        Set<Transaction> expectedTop10Expenses1 = new TreeSet<>();
        expectedTop10Expenses1.add(new Transaction("30-01-2017", -100, "Deliveroo"));
        assertEquals(expectedTop10Expenses1, top10Expenses1);
        Set<Transaction> transactions;
        transactions = BankTransactionProcessor.processBankTransactionsFile("src\\test\\resources\\TestBankTransactionsLotOfExpenses.csv");
        bankTransactionAnalyzer = new BankTransactionAnalyzer(transactions, "src\\test\\resources\\TestBankTransactions.csv");
        //System.out.println(Transaction.printListTransactions(bankTransactionAnalyzer.getOrderedTransactions()));
        assertEquals(15, bankTransactionAnalyzer.getBankTransactionsCount());//one transaction is the same
        Set<Transaction> top10Expenses2 = bankTransactionAnalyzer.getTop10Expenses();
        //System.out.println("top10Expenses2" + top10Expenses2);
        Set<Transaction> expectedTop10Expenses2 = new TreeSet<>();
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -135, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -1500, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -900, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -800, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -700, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -600, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -500, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -150, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -130, "Deliveroo"));
        expectedTop10Expenses2.add(new Transaction("30-01-2017", -1000, "Deliveroo"));
        assertEquals(expectedTop10Expenses2, top10Expenses2);
    }

    @Test
    void testCorrectCategoryWithMostExpenses() {
        assertEquals("Food", bankTransactionAnalyzer.getCategoryWithMostExpenses());
    }

    @Test
    void testBankTransactionsCountInJanuary() {
        assertEquals(1, bankTransactionAnalyzer.getBankTransactionsCount("January"));
    }

    @Test
    void testBankTransactionsCountInFebruary() {
        assertEquals(1, bankTransactionAnalyzer.getBankTransactionsCount("February"));
    }

    @Test
    void printOrderedTransactions() {
        String expectedPrint = "Transaction[date=30-01-2017,amount=-100.0,entity=Deliveroo]" + NEWLINE +
                "Transaction[date=01-02-2017,amount=6000.0,entity=Salary]";
                /*
                30-01-2017,-100,Deliveroo
                01-02-2017,6000,Salary
                */
        assertEquals(expectedPrint, Transaction.getListTransactionsToPrint(bankTransactionAnalyzer.getOrderedTransactions()));
    }
}