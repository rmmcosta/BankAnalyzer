package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
    public static void main(String[] args) {
        List<BankTransaction> transactions = bankStatementCSVParser.parseStatements(FileHandle.getFileLines("src/main/resources/BankTransactions.csv"));
        BankTransactionProcessor bankTransactionProcessor = new BankTransactionProcessor(transactions, "src/main/resources/categories.csv");
        printSummary(bankTransactionProcessor);
    }

    private static void printSummary(BankTransactionProcessor bankTransactionProcessor) {
        /*
        He would like to get an answer for the following queries:
            • What is the total profit and loss from a list of bank statements? Is it positive or
            negative?
            • How many bank bankTransactions are there in a particular month?
            • What are his top-10 expenses?
            • Which category does he spend most of his money on?
         */
        System.out.println("Total profit and loss: " + bankTransactionProcessor.calculateTotalProfitAndLoss());
        System.out.println("Top 10 expenses: ");
        System.out.println(BankTransaction.getListTransactionsToPrint(bankTransactionProcessor.calculateTop10Expenses()));
        System.out.println("Category with most money spent: " + bankTransactionProcessor.calculateCategoryWithMostExpenses());
        System.out.println("Bank bankTransactions count in January: " + bankTransactionProcessor.calculateTransactionsCountPerMonth(Month.JANUARY));
        System.out.println("Bank bankTransactions count in February: " + bankTransactionProcessor.calculateTransactionsCountPerMonth(Month.FEBRUARY));
    }
}
