package com.sgkata.bankaccount.domain.service;

import com.sgkata.bankaccount.application.dto.TransactionDto;
import com.sgkata.bankaccount.domain.exception.TransactionFunctionalRuleException;
import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.port.AccountPersistence;
import com.sgkata.bankaccount.domain.port.TransactionPersistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private AccountPersistence accountPersistance;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        accountPersistance = mock(AccountPersistence.class);
        TransactionPersistance transactionPersistance =  mock(TransactionPersistance.class);
        transactionService = new TransactionService(accountPersistance,transactionPersistance);
    }

    @Test
    void newTransaction_successfulTransaction() {
        // Arrange
        String sourceAccountId = "source123";
        String targetAccountId = "target456";
        BigDecimal amount = new BigDecimal("100.00");

        TransactionDto transactionDto = new TransactionDto(
                null,
                sourceAccountId,
                targetAccountId,
                LocalDateTime.now(),
                amount
        );

        Account sourceAccount = new Account();
        sourceAccount.setAccountId(sourceAccountId);
        sourceAccount.setBalance(new BigDecimal("100.00"));

        Account targetAccount = new Account();
        targetAccount.setAccountId(targetAccountId);
        targetAccount.setBalance(new BigDecimal("300.00"));

        when(accountPersistance.getAccountById(targetAccountId)).thenReturn(targetAccount);
        when(accountPersistance.getAccountById(sourceAccountId)).thenReturn(sourceAccount);


        // Act
        transactionService.newTransaction(transactionDto);

        // Assert
        verify(accountPersistance, times(1)).getAccountById(targetAccountId);
        verify(accountPersistance, times(1)).getAccountById(sourceAccountId);
        verify(accountPersistance, times(1)).save(targetAccount);
    }

    @Test
    void newTransaction_insufficientBalance() {
        // Arrange
        String sourceAccountId = "source123";
        String targetAccountId = "target456";
        BigDecimal amount = new BigDecimal("300.00");

        TransactionDto transactionDto = new TransactionDto(
                null,
                sourceAccountId,
                targetAccountId,
                LocalDateTime.now(),
                amount
        );

        Account sourceAccount = new Account();
        sourceAccount.setAccountId(targetAccountId);
        sourceAccount.setBalance(new BigDecimal("200.00"));

        when(accountPersistance.getAccountById(targetAccountId)).thenReturn(sourceAccount);

        // Act & Assert
        TransactionFunctionalRuleException exception = assertThrows(
                TransactionFunctionalRuleException.class,
                () -> transactionService.newTransaction(transactionDto)
        );

        assertEquals("Target Account does not exist", exception.getMessage());
        verify(accountPersistance, times(1)).getAccountById(targetAccountId);
        verify(accountPersistance, never()).save(any(Account.class));
    }

    @Test
    void newTransaction_nullAccount() {
        // Arrange
        String targetAccountId = "target456";
        TransactionDto transactionDto = new TransactionDto(
                null,
                "source123",
                targetAccountId,
                LocalDateTime.now(),
                new BigDecimal("100.00")
        );

        when(accountPersistance.getAccountById(targetAccountId)).thenReturn(null);

        // Act & Assert
        TransactionFunctionalRuleException exception = assertThrows(
                TransactionFunctionalRuleException.class,
                () -> transactionService.newTransaction(transactionDto)
        );

        assertNotNull(exception);
        verify(accountPersistance, times(1)).getAccountById(targetAccountId);
        verify(accountPersistance, never()).save(any(Account.class));
    }
}
