package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class BankTransactionProcessor {
    private final List<BankTransaction> _transaction;
    private final Map<String, Double> _amountPerCategory;

    public BankTransactionProcessor(List<BankTransaction> transactions, String categoriesFilePath) {
        _transaction = transactions;
        _amountPerCategory = new HashMap<>();

        ICategoriesProcessor categoriesProcessor = new CategoriesProcessor(categoriesFilePath);
        for (BankTransaction bankTransaction : transactions) {
            String category = categoriesProcessor.getCategory(bankTransaction.entity());
            if (_amountPerCategory.containsKey(category)) {
                _amountPerCategory.replace(category, _amountPerCategory.get(category) + bankTransaction.amount());
            } else {
                _amountPerCategory.put(category, bankTransaction.amount());
            }
        }
    }

    public int calculateTransactionsCount() {
        return _transaction.size();
    }

    public double calculateTotalProfitAndLoss() {
        return _transaction.stream().mapToDouble(BankTransaction::amount).sum();
    }

    public List<BankTransaction> calculateTop10Expenses() {
        return _transaction.stream()
                .filter(transaction -> transaction.amount() < 0)
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    public String calculateCategoryWithMostExpenses() {
        return _amountPerCategory.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
    }

    public List<BankTransaction> getTransactions() {
        return _transaction;
    }

    //How many bank transactions are there in a particular month?
    public int calculateTransactionsCountPerMonth(Month month) {
        return (int) _transaction.stream()
                .filter(transaction -> transaction.date().getMonth().equals(month))
                .count();
    }


}
