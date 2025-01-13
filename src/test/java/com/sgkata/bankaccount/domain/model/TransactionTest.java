package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.TransactionPersistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionTest {

    private Transaction transaction;
    private TransactionPersistance transactionPersistance;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transactionPersistance = mock(TransactionPersistance.class);
    }

    @Test
    void testSaveCallsTransactionPersistance() {
        // Arrange
        transaction.setId("txn123");
        transaction.setTargetAccount("ACC456");
        transaction.setSourceAccount("ACC001");
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(new BigDecimal("100.50"));

        // Act
        transaction.save(transactionPersistance);

        // Assert
        verify(transactionPersistance, times(1)).save(transaction);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        String id = "txn789";
        String accountNum = "ACC123";
        LocalDateTime date = LocalDateTime.of(2025, 1, 1, 12, 0);
        BigDecimal amount = new BigDecimal("250.75");

        // Act
        transaction.setId(id);
        transaction.setTargetAccount(accountNum);
        transaction.setSourceAccount("ACC001");
        transaction.setDate(date);
        transaction.setAmount(amount);

        // Assert
        assertEquals(id, transaction.getId());
        assertEquals(accountNum, transaction.getTargetAccount());
        assertEquals(date, transaction.getDate());
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    void testSaveDoesNotThrowException() {
        // Arrange
        transaction.setId("txn999");
        transaction.setTargetAccount("ACC888");
        transaction.setSourceAccount("ACC001");
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(new BigDecimal("300.00"));

        // Act & Assert
        assertDoesNotThrow(() -> transaction.save(transactionPersistance));
    }
}

