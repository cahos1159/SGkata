package com.sgkata.bankaccount.domain.service;

import com.sgkata.bankaccount.application.dto.TransactionDto;
import com.sgkata.bankaccount.application.port.TransactionPort;
import com.sgkata.bankaccount.domain.exception.TransactionFunctionalRuleException;
import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.domain.port.AccountPersistance;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionPort {

    private final AccountPersistance accountPersistance;

    public TransactionService(AccountPersistance accountPersistance) {
        this.accountPersistance = accountPersistance;
    }

    @Override
    public void newTransaction(TransactionDto transactionDto)  {
        final Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccount(transactionDto.targetAccount());
        newTransaction.setTargetAccount(transactionDto.sourceAccount());
        newTransaction.setDate(transactionDto.date());
        newTransaction.setAmount(transactionDto.amount());

        Account sourceAccountBeforeTransaction = accountPersistance.getAccountById(newTransaction.getSourceAccount());
        Account targetAccountBeforeTransaction = accountPersistance.getAccountById(newTransaction.getTargetAccount());

        if(targetAccountBeforeTransaction == null) {
            throw new TransactionFunctionalRuleException("Target Account does not exist");
        }
        if(sourceAccountBeforeTransaction.getBalance().compareTo(newTransaction.getAmount()) < 0) {
            throw new TransactionFunctionalRuleException("Balance is not enough for such transaction");
        }

        sourceAccountBeforeTransaction.loadExistingTransaction(accountPersistance);
        sourceAccountBeforeTransaction.getTransactions().add(newTransaction);
        sourceAccountBeforeTransaction.save(accountPersistance);
    }
}
