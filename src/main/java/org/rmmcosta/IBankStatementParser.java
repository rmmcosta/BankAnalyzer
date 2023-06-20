package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

import java.util.List;

public interface IBankStatementParser {
    List<BankTransaction> parseStatements(List<String> statements);
    BankTransaction parseStatement(String statement);
}
