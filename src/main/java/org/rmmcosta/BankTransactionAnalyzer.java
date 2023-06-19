package org.rmmcosta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BankTransactionAnalyzer {
    private final Set<Transaction> _orderedTransactions;
    private final Map<String, Double> _amountPerCategory;

    public BankTransactionAnalyzer(Set<Transaction> orderedTransactions, String categoriesFilePath) {
        _orderedTransactions = orderedTransactions;
        _amountPerCategory = new HashMap<>();

        ICategoriesProcessor categoriesProcessor = new CategoriesProcessor(categoriesFilePath);
        for (Transaction transaction : orderedTransactions) {
            String category = categoriesProcessor.getCategory(transaction.entity());
            if (_amountPerCategory.containsKey(category)) {
                _amountPerCategory.replace(category, _amountPerCategory.get(category) + transaction.amount());
            } else {
                _amountPerCategory.put(category, transaction.amount());
            }
        }
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
