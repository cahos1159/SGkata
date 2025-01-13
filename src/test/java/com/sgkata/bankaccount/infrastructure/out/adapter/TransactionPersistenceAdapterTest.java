package com.sgkata.bankaccount.infrastructure.out.adapter;

import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.infrastructure.mapper.TransactionMapper;
import com.sgkata.bankaccount.infrastructure.out.entity.TransactionJpaEntity;
import com.sgkata.bankaccount.infrastructure.out.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TransactionPersistenceAdapterTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionPersistanceAdapter transactionPersistanceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId("12345");
        transaction.setSourceAccount("source123");
        transaction.setTargetAccount("target456");
        transaction.setAmount(new java.math.BigDecimal("100.00"));
        transaction.setDate(java.time.LocalDateTime.now());

        TransactionJpaEntity transactionJpaEntity = new TransactionJpaEntity();
        when(transactionMapper.mapToTransactionJpaEntity(transaction)).thenReturn(transactionJpaEntity);

        // Act
        transactionPersistanceAdapter.save(transaction);

        // Assert
        verify(transactionRepository, times(1)).save(transactionJpaEntity);
    }
}
