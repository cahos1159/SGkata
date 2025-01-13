package com.sgkata.bankaccount.domain.service;

import com.sgkata.bankaccount.application.dto.TransactionDto;
import com.sgkata.bankaccount.application.port.TransactionPort;
import com.sgkata.bankaccount.domain.exception.TransactionFunctionalRuleException;
import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.domain.port.AccountPersistence;
import com.sgkata.bankaccount.domain.port.TransactionPersistance;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionPort {

    private final AccountPersistence accountPersistance;
    private final TransactionPersistance transactionPersistance;

    public TransactionService(AccountPersistence accountPersistance, TransactionPersistance transactionPersistance) {
        this.accountPersistance = accountPersistance;
        this.transactionPersistance = transactionPersistance;
    }

    @Override
    @Transactional
    public void newTransaction(TransactionDto transactionDto)  {
        final Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccount(transactionDto.targetAccount());
        newTransaction.setTargetAccount(transactionDto.sourceAccount());
        newTransaction.setDate(transactionDto.date());
        newTransaction.setAmount(transactionDto.amount());

        Account sourceAccount = accountPersistance.getAccountById(newTransaction.getSourceAccount());
        Account targetAccount = accountPersistance.getAccountById(newTransaction.getTargetAccount());

        if(targetAccount == null) {
            throw new TransactionFunctionalRuleException("Target Account does not exist");
        }
        if(sourceAccount.getBalance().compareTo(newTransaction.getAmount()) < 0) {
            throw new TransactionFunctionalRuleException("Balance is not enough for such transaction");
        }
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(newTransaction.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().add(newTransaction.getAmount()));
        newTransaction.save(transactionPersistance);
        sourceAccount.save(accountPersistance);
        targetAccount.save(accountPersistance);
    }
}
