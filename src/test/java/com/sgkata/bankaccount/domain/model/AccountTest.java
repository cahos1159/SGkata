package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.AccountPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTest {

    private Account account;
    private AccountPersistence accountPersistance;

    @BeforeEach
    void setUp() {
        account = new Account();
        accountPersistance = mock(AccountPersistence.class);
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
    void testGettersAndSetters() {
        // Arrange
        String accountId = "acc456";
        String owner = "Jane Smith";
        BigDecimal balance = new BigDecimal("2000.50");

        // Act
        account.setAccountId(accountId);
        account.setOwner(owner);
        account.setBalance(balance);

        // Assert
        assertEquals(accountId, account.getAccountId());
        assertEquals(owner, account.getOwner());
        assertEquals(balance, account.getBalance());
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

