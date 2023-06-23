package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class BankTransactionProcessor {
    private final List<BankTransaction> bankTransactions;
    private final Map<String, Double> amountPerCategory;

    private final ICategoriesProcessor categoriesProcessor;

    public BankTransactionProcessor(List<BankTransaction> transactions, String categoriesFilePath) {
        bankTransactions = transactions;
        amountPerCategory = new HashMap<>();

        categoriesProcessor = new CategoriesProcessor(categoriesFilePath);
        for (BankTransaction bankTransaction : transactions) {
            String category = categoriesProcessor.getCategory(bankTransaction.entity());
            if (amountPerCategory.containsKey(category)) {
                amountPerCategory.replace(category, amountPerCategory.get(category) + bankTransaction.amount());
            } else {
                amountPerCategory.put(category, bankTransaction.amount());
            }
        }
    }

    public String getCategoryByEntity(String entity) {
        return categoriesProcessor.getCategory(entity);
    }

    public int calculateTransactionsCount() {
        return bankTransactions.size();
    }

    public double calculateTotalProfitAndLoss() {
        return bankTransactions.stream().mapToDouble(BankTransaction::amount).sum();
    }

    public List<BankTransaction> calculateTop10Expenses() {
        return bankTransactions.stream()
                .filter(transaction -> transaction.amount() < 0)
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    public String calculateCategoryWithMostExpenses() {
        return amountPerCategory.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
    }

    public List<BankTransaction> getTransactions() {
        return bankTransactions;
    }

    //How many bank transactions are there in a particular month?
    public int calculateTransactionsCountPerMonth(Month month) {
        return (int) bankTransactions.stream()
                .filter(transaction -> transaction.date().getMonth().equals(month))
                .count();
    }


    public List<BankTransaction> filterTransactionsGreaterThan(double amount) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.amount() > amount)
                .toList();
    }

    public List<BankTransaction> filterTransactionsGreaterThanByCategory(double amount, String category) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.amount() > amount && getCategoryByEntity(bankTransaction.entity()).equals(category))
                .toList();
    }

    public List<BankTransaction> filterTransactionsBetweenDates(LocalDate beginDate, LocalDate endDate) {
        return bankTransactions.stream()
                .filter(bankTransaction -> !bankTransaction.date().isBefore(beginDate) && !bankTransaction.date().isAfter(endDate))
                .toList();
    }

    public List<BankTransaction> filterTransactions(IBankStatementFilter bankStatementFilter) {
        return bankTransactions.stream()
                .filter(bankStatementFilter::test)
                .toList();
    }
}
