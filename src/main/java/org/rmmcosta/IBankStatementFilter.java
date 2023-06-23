package org.rmmcosta;

import org.rmmcosta.domain.BankTransaction;

public interface IBankStatementFilter {
    boolean test(BankTransaction bankTransaction);
}
