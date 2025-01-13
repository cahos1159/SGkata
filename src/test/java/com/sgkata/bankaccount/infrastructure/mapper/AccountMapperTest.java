package com.sgkata.bankaccount.infrastructure.mapper;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.infrastructure.out.entity.AccountJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        accountMapper = new AccountMapper();
    }

    @Test
    void mapToAccountJpaEntity_withValidAccount() {
        // Arrange
        Account account = new Account();
        account.setAccountId("12345");
        account.setBalance(new BigDecimal("1000.00"));
        account.setOwner("John Doe");

        // Act
        AccountJpaEntity result = accountMapper.mapToAccountJpaEntity(account);

        // Assert
        assertNotNull(result);
        assertEquals("12345", result.getAccountId());
        assertEquals(new BigDecimal("1000.00"), result.getBalance());
        assertEquals("John Doe", result.getOwner());
    }

    @Test
    void mapToAccountJpaEntity_withNullAccount() {
        // Act
        AccountJpaEntity result = accountMapper.mapToAccountJpaEntity(null);

        // Assert
        assertNotNull(result);
        assertNull(result.getAccountId());
        assertNull(result.getBalance());
        assertNull(result.getOwner());
    }

    @Test
    void mapToAccount_withValidAccountJpaEntity() {
        // Arrange
        AccountJpaEntity accountJpaEntity = new AccountJpaEntity();
        accountJpaEntity.setAccountId("12345");
        accountJpaEntity.setBalance(new BigDecimal("1000.00"));
        accountJpaEntity.setOwner("Jane Doe");

        // Act
        Account result = accountMapper.mapToAccount(accountJpaEntity);

        // Assert
        assertNotNull(result);
        assertEquals("12345", result.getAccountId());
        assertEquals(new BigDecimal("1000.00"), result.getBalance());
        assertEquals("Jane Doe", result.getOwner());
    }

    @Test
    void mapToAccount_withNullAccountJpaEntity() {
        // Act
        Account result = accountMapper.mapToAccount(null);

        // Assert
        assertNotNull(result);
        assertNull(result.getAccountId());
        assertNull(result.getBalance());
        assertNull(result.getOwner());
    }

    @Test
    void mapToAccountJpaEntity_withEmptyAccount() {
        // Arrange
        Account account = new Account();

        // Act
        AccountJpaEntity result = accountMapper.mapToAccountJpaEntity(account);

        // Assert
        assertNotNull(result);
        assertNull(result.getAccountId());
        assertNull(result.getBalance());
        assertNull(result.getOwner());
    }

    @Test
    void mapToAccount_withEmptyAccountJpaEntity() {
        // Arrange
        AccountJpaEntity accountJpaEntity = new AccountJpaEntity();

        // Act
        Account result = accountMapper.mapToAccount(accountJpaEntity);

        // Assert
        assertNotNull(result);
        assertNull(result.getAccountId());
        assertNull(result.getBalance());
        assertNull(result.getOwner());
    }
}
