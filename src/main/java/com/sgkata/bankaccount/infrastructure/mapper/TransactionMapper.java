package com.sgkata.bankaccount.infrastructure.mapper;

import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.infrastructure.out.entity.TransactionJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper {

    public TransactionJpaEntity mapToTransactionJpaEntity(Transaction transaction) {
        TransactionJpaEntity result = new TransactionJpaEntity();
        if(transaction == null){
            return result;
        }
        if (transaction.getId() != null) {
            result.setId(transaction.getId());
        }
        result.setDate(transaction.getDate());
        result.setSourceAccount(transaction.getSourceAccount());
        result.setTargetAccount(transaction.getTargetAccount());
        result.setAmount(transaction.getAmount());
        return result;
    }

    public Transaction mapToTransaction(TransactionJpaEntity transactionJpaEntity) {
        Transaction result = new Transaction();
        if(transactionJpaEntity == null){
            return result;
        }
        if (transactionJpaEntity.getId() != null) {
            result.setId(transactionJpaEntity.getId());
        }
        result.setDate(transactionJpaEntity.getDate());
        result.setSourceAccount(transactionJpaEntity.getSourceAccount());
        result.setTargetAccount(transactionJpaEntity.getTargetAccount());
        result.setAmount(transactionJpaEntity.getAmount());
        return result;
    }

    public List<Transaction> mapListOfEntityToTransaction(List<TransactionJpaEntity> transactionJpaEntityList){
        return transactionJpaEntityList.stream().map(this::mapToTransaction).toList();
    }

}
