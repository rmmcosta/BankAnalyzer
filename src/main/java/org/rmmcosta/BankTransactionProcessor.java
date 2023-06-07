package org.rmmcosta;

import java.util.*;
import java.util.stream.Collectors;

import static org.rmmcosta.FileHandle.getFileLines;

public class BankTransactionProcessor {
    private final Set<Transaction> _orderedTransactions = new TreeSet<>();

    private final Map<String, Double> _amountPerCategory = new HashMap<>();

    private static BankTransactionProcessor _bankTransactionProcessor;

    private BankTransactionProcessor(String[] bankTransactions, ICategoriesProcessor categoriesProcessor) {
        processBankTransactions(bankTransactions, categoriesProcessor);
    }

    private void processBankTransactions(String[] bankTransactions, ICategoriesProcessor categoriesProcessor) {
        if (!_orderedTransactions.isEmpty()) {
            _orderedTransactions.clear();
        }
        if (!_amountPerCategory.isEmpty()) {
            _amountPerCategory.clear();
        }
        Arrays.stream(bankTransactions).forEach(line -> {
            String[] lineSplit = line.split(",");
            String date = lineSplit[0].trim();
            double amount = Double.parseDouble(lineSplit[1].trim());
            String entity = lineSplit[2].trim();
            _orderedTransactions.add(new Transaction(date, amount, entity));
            String category = categoriesProcessor.getCategory(entity);
            if (_amountPerCategory.containsKey(category)) {
                _amountPerCategory.replace(category, _amountPerCategory.get(category) + amount);
            } else {
                _amountPerCategory.put(category, amount);
            }
        });
    }

    public static BankTransactionProcessor processBankTransactionsFile(String filePath, ICategoriesProcessor categoriesProcessor) {
        /*
        He would like to get an answer for the following queries:
            • What is the total profit and loss from a list of bank statements? Is it positive or
            negative?
            • How many bank transactions are there in a particular month?
            • What are his top-10 expenses?
            • Which category does he spend most of his money on?
         */
        if (_bankTransactionProcessor == null) {
            _bankTransactionProcessor = new BankTransactionProcessor(getFileLines(filePath), categoriesProcessor);
        } else {
            _bankTransactionProcessor.processBankTransactions(getFileLines(filePath), categoriesProcessor);
        }
        return _bankTransactionProcessor;
    }

    public int getBankTransactionsCount() {
        return _orderedTransactions.size();
    }

    public double getTotalProfitAndLoss() {
        return _orderedTransactions.stream().mapToDouble(Transaction::amount).sum();
    }

    public Set<Transaction> getTop10Expenses() {
        return _orderedTransactions.stream().filter(transaction -> transaction.amount() < 0).limit(10).collect(Collectors.toSet());
    }

    public String getCategoryWithMostExpenses() {
        return _amountPerCategory.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
    }

    public Set<Transaction> getOrderedTransactions() {
        return _orderedTransactions;
    }

    //How many bank transactions are there in a particular month?
    public int getBankTransactionsCount(String month) {
        return (int) _orderedTransactions.stream()
                .filter(transaction -> Transaction.getTransactionMonth(transaction.date()).equals(month))
                .count();
    }
}
