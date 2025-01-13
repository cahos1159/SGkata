package com.sgkata.bankaccount.infrastructure.out.adapter;

import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.domain.port.TransactionPersistance;
import com.sgkata.bankaccount.infrastructure.mapper.TransactionMapper;
import com.sgkata.bankaccount.infrastructure.out.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionPersistanceAdapter implements TransactionPersistance {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionPersistanceAdapter(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transactionMapper.mapToTransactionJpaEntity(transaction));
    }

}
