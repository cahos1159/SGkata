package com.sgkata.bankaccount.infrastructure.out.adapter;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.domain.port.AccountPersistence;
import com.sgkata.bankaccount.infrastructure.out.entity.AccountJpaEntity;
import com.sgkata.bankaccount.infrastructure.mapper.AccountMapper;
import com.sgkata.bankaccount.infrastructure.mapper.TransactionMapper;
import com.sgkata.bankaccount.infrastructure.out.repository.AccountRepository;
import com.sgkata.bankaccount.infrastructure.out.repository.TransactionRepository;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountPersistenceAdapter implements AccountPersistence {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private  final TransactionMapper transactionMapper;
    private  final AccountMapper accountMapper;

    public AccountPersistenceAdapter(AccountRepository accountRepository, TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(accountMapper.mapToAccountJpaEntity(account));
    }

    @Override
    public Account getAccountById(String id) {
        AccountJpaEntity accountJpaEntity = accountRepository.findById(id).orElseThrow( () -> new ExecutionException("No Account with this ID found"));
        return accountMapper.mapToAccount(accountJpaEntity);
    }

    @Override
    public List<Transaction> loadExistingTransactions(String accountId) {
        return transactionMapper.mapListOfEntityToTransaction(transactionRepository.findAllTransaction(accountId));
    }
}
