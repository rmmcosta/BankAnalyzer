package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankStatementCSVParser implements IBankStatementParser {
    @Override
    public List<BankTransaction> parseStatements(List<String> statements) {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        statements.forEach(line -> {
            bankTransactions.add(parseStatement(line));
        });
        return bankTransactions;
    }

    @Override
    public BankTransaction parseStatement(String line) {
        String[] lineSplit = line.split(",");
        LocalDate date = LocalDate.parse(lineSplit[0].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        double amount = Double.parseDouble(lineSplit[1].trim());
        String entity = lineSplit[2].trim();
        return new BankTransaction(date, amount, entity);
    }
}
