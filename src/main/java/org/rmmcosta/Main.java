package org.rmmcosta;

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
        ICategoriesProcessor categoriesProcessor = new CategoriesProcessor();
        categoriesProcessor.processCategoriesFile("src/main/resources/categories.csv");
        BankTransactionProcessor bankTransactionProcessor = BankTransactionProcessor.processBankTransactionsFile("src/main/resources/BankTransactions.csv", categoriesProcessor);
        System.out.println("Total profit and loss: " + bankTransactionProcessor.getTotalProfitAndLoss());
        System.out.println("Top 10 expenses: ");
        System.out.println(Transaction.getListTransactionsToPrint(bankTransactionProcessor.getTop10Expenses()));
        System.out.println("Category with most money spent: " + bankTransactionProcessor.getCategoryWithMostExpenses());
        System.out.println("Bank transactions count in January: " + bankTransactionProcessor.getBankTransactionsCount("January"));
        System.out.println("Bank transactions count in February: " + bankTransactionProcessor.getBankTransactionsCount("February"));
    }
}