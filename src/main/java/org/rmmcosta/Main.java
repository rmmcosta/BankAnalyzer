package org.rmmcosta;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /*
        He would like to get an answer for the following queries:
            • What is the total profit and loss from a list of bank statements? Is it positive or
            negative?
            • How many bank transactions are there in a particular month?
            • What are his top-10 expenses?
            • Which category does he spend most of his money on?
         */
        Set<Transaction> transactions = BankTransactionProcessor.processBankTransactionsFile("src/main/resources/BankTransactions.csv");
        BankTransactionAnalyzer bankTransactionAnalyzer = new BankTransactionAnalyzer(transactions, "src/main/resources/categories.csv");
        System.out.println("Total profit and loss: " + bankTransactionAnalyzer.getTotalProfitAndLoss());
        System.out.println("Top 10 expenses: ");
        System.out.println(Transaction.getListTransactionsToPrint(bankTransactionAnalyzer.getTop10Expenses()));
        System.out.println("Category with most money spent: " + bankTransactionAnalyzer.getCategoryWithMostExpenses());
        System.out.println("Bank transactions count in January: " + bankTransactionAnalyzer.getBankTransactionsCount("January"));
        System.out.println("Bank transactions count in February: " + bankTransactionAnalyzer.getBankTransactionsCount("February"));
    }
}