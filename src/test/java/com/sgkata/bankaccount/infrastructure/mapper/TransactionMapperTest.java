package com.sgkata.bankaccount.infrastructure.mapper;

import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.infrastructure.out.entity.TransactionJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        transactionMapper = new TransactionMapper();
    }

    @Test
    void mapToTransactionJpaEntity_withValidTransaction() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId("txn123");
        transaction.setDate(LocalDateTime.now());
        transaction.setSourceAccount("source123");
        transaction.setTargetAccount("target456");
        transaction.setAmount(new BigDecimal("100.00"));

        // Act
        TransactionJpaEntity result = transactionMapper.mapToTransactionJpaEntity(transaction);

        // Assert
        assertNotNull(result);
        assertEquals("txn123", result.getId());
        assertEquals("source123", result.getSourceAccount());
        assertEquals("target456", result.getTargetAccount());
        assertEquals(new BigDecimal("100.00"), result.getAmount());
        assertNotNull(result.getDate());
    }

    @Test
    void mapToTransactionJpaEntity_withNullTransaction() {
        // Act
        TransactionJpaEntity result = transactionMapper.mapToTransactionJpaEntity(null);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getSourceAccount());
        assertNull(result.getTargetAccount());
        assertNull(result.getAmount());
        assertNull(result.getDate());
    }

    @Test
    void mapToTransaction_withValidTransactionJpaEntity() {
        // Arrange
        TransactionJpaEntity transactionJpaEntity = new TransactionJpaEntity();
        transactionJpaEntity.setId("txn123");
        transactionJpaEntity.setDate(LocalDateTime.now());
        transactionJpaEntity.setSourceAccount("source123");
        transactionJpaEntity.setTargetAccount("target456");
        transactionJpaEntity.setAmount(new BigDecimal("100.00"));

        // Act
        Transaction result = transactionMapper.mapToTransaction(transactionJpaEntity);

        // Assert
        assertNotNull(result);
        assertEquals("txn123", result.getId());
        assertEquals("source123", result.getSourceAccount());
        assertEquals("target456", result.getTargetAccount());
        assertEquals(new BigDecimal("100.00"), result.getAmount());
        assertNotNull(result.getDate());
    }

    @Test
    void mapToTransaction_withNullTransactionJpaEntity() {
        // Act
        Transaction result = transactionMapper.mapToTransaction(null);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getSourceAccount());
        assertNull(result.getTargetAccount());
        assertNull(result.getAmount());
        assertNull(result.getDate());
    }

    @Test
    void mapListOfEntityToTransaction_withValidList() {
        // Arrange
        TransactionJpaEntity entity1 = new TransactionJpaEntity();
        entity1.setId("txn1");
        entity1.setDate(LocalDateTime.now());
        entity1.setSourceAccount("source1");
        entity1.setTargetAccount("target1");
        entity1.setAmount(new BigDecimal("50.00"));

        TransactionJpaEntity entity2 = new TransactionJpaEntity();
        entity2.setId("txn2");
        entity2.setDate(LocalDateTime.now());
        entity2.setSourceAccount("source2");
        entity2.setTargetAccount("target2");
        entity2.setAmount(new BigDecimal("75.00"));

        List<TransactionJpaEntity> entities = List.of(entity1, entity2);

        // Act
        List<Transaction> result = transactionMapper.mapListOfEntityToTransaction(entities);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("txn1", result.get(0).getId());
        assertEquals("txn2", result.get(1).getId());
    }

    @Test
    void mapListOfEntityToTransaction_withEmptyList() {
        // Act
        List<Transaction> result = transactionMapper.mapListOfEntityToTransaction(List.of());

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
