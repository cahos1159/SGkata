package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.AccountPersistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTests {

    private Account account;
    private AccountPersistance accountPersistance;

    @BeforeEach
    void setUp() {
        account = new Account();
        accountPersistance = mock(AccountPersistance.class);
    }

    @Test
    void testSaveCallsAccountPersistance() {
        // Arrange
        account.setAccountId("acc123");
        account.setOwner("John Doe");
        account.setBalance(new BigDecimal("1000.00"));

        // Act
        account.save(accountPersistance);

        // Assert
        verify(accountPersistance, times(1)).save(account);
    }

    @Test
    void testLoadExistingTransactionCallsAccountPersistance() {
        // Arrange
        String accountId = "acc123";
        account.setAccountId(accountId);
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction());
        when(accountPersistance.loadExistingTransactions(accountId)).thenReturn(mockTransactions);

        // Act
        List<Transaction> transactions = account.loadExistingTransaction(accountPersistance);

        // Assert
        verify(accountPersistance, times(1)).loadExistingTransactions(accountId);
        assertEquals(mockTransactions, transactions);
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        String accountId = "acc456";
        String owner = "Jane Smith";
        BigDecimal balance = new BigDecimal("2000.50");
        List<Transaction> transactions = new ArrayList<>();

        // Act
        account.setAccountId(accountId);
        account.setOwner(owner);
        account.setBalance(balance);
        account.setTransactions(transactions);

        // Assert
        assertEquals(accountId, account.getAccountId());
        assertEquals(owner, account.getOwner());
        assertEquals(balance, account.getBalance());
        assertEquals(transactions, account.getTransactions());
    }

    @Test
    void testSaveDoesNotThrowException() {
        // Arrange
        account.setAccountId("acc789");
        account.setOwner("Alice Brown");
        account.setBalance(new BigDecimal("500.00"));

        // Act & Assert
        assertDoesNotThrow(() -> account.save(accountPersistance));
    }
}

