package org.rmmcosta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rmmcosta.domain.BankTransaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankBankTransactionProcessorTest {
    private static final String NEWLINE = "\n";

    IBankStatementParser bankStatementParser;

    BankTransactionProcessor bankTransactionProcessor;

    @BeforeEach
    void setUp() {
        bankStatementParser = new BankStatementCSVParser();

        bankTransactionProcessor = new BankTransactionProcessor(
                bankStatementParser.parseStatements(
                        FileHandle.getFileLines("src\\test\\resources\\TestBankTransactions.csv")
                ),
                "src\\test\\resources\\TestCategories.csv");
    }

    @Test
    void testBankTransactionsCount() {
        assertEquals(2, bankTransactionProcessor.calculateTransactionsCount());
    }

    @Test
    void testTotalProfitAndLoss() {
        assertEquals(5900, bankTransactionProcessor.calculateTotalProfitAndLoss());
    }

    @Test
    void testTop10Expenses() {
        List<BankTransaction> top10Expenses1 = bankTransactionProcessor.calculateTop10Expenses();
        List<BankTransaction> expectedTop10Expenses1 = new ArrayList<>();
        expectedTop10Expenses1.add(new BankTransaction(LocalDate.of(2017, 1, 30), -100, "Deliveroo"));
        assertEquals(expectedTop10Expenses1, top10Expenses1);

        BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
        bankTransactionProcessor = new BankTransactionProcessor(
                bankStatementCSVParser.parseStatements(FileHandle.getFileLines("src\\test\\resources\\TestBankTransactionsLotOfExpenses.csv")),
                "src\\test\\resources\\TestCategories.csv"
        );

        //System.out.println(BankTransaction.printListTransactions(bankTransactionProcessor.calculateOrderedTransactions()));
        assertEquals(15, bankTransactionProcessor.calculateTransactionsCount());//one transaction is the same
        List<BankTransaction> top10Expenses2 = bankTransactionProcessor.calculateTop10Expenses();
        //System.out.println("top10Expenses2" + top10Expenses2);
        List<BankTransaction> expectedTop10Expenses2 = new ArrayList<>();
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -1500, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -1000, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -900, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -800, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -700, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -600, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -500, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -150, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -135, "Deliveroo"));
        expectedTop10Expenses2.add(new BankTransaction(LocalDate.of(2017, 1, 30), -130, "Deliveroo"));
        assertEquals(expectedTop10Expenses2, top10Expenses2);
    }

    @Test
    void testCorrectCategoryWithMostExpenses() {
        assertEquals("Food", bankTransactionProcessor.calculateCategoryWithMostExpenses());
    }

    @Test
    void testBankTransactionsCountInJanuary() {
        assertEquals(1, bankTransactionProcessor.calculateTransactionsCountPerMonth(Month.JANUARY));
    }

    @Test
    void testBankTransactionsCountInFebruary() {
        assertEquals(1, bankTransactionProcessor.calculateTransactionsCountPerMonth(Month.FEBRUARY));
    }

    @Test
    void printOrderedTransactions() {
        String expectedPrint = "BankTransaction[date=2017-01-30,amount=-100.0,entity=Deliveroo]" + NEWLINE +
                "BankTransaction[date=2017-02-01,amount=6000.0,entity=Salary]";
                /*
                30-01-2017,-100,Deliveroo
                01-02-2017,6000,Salary
                */
        assertEquals(expectedPrint, BankTransaction.getListTransactionsToPrint(bankTransactionProcessor.getTransactions()));
    }
}