package com.sgkata.bankaccount.infrastructure.out;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.domain.port.AccountPersistance;
import org.springframework.stereotype.Service;

import java.util.Collections;

import java.util.List;

@Service
public class AccountPersistanceAdapter implements AccountPersistance {
    @Override
    public void save(Account account) {
        // TODO document why this method is empty
    }

    @Override
    public Account getAccountById(String id) {
        return null;
    }

    @Override
    public List<Transaction> loadExistingTransactions(String accountId) {
        return Collections.emptyList();
    }
}
