package com.sgkata.bankaccount.domain.port;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;

import java.util.List;

public interface AccountPersistance {

    void save(Account account);

    List<Transaction> loadExistingTransactions(String accountId);

}
