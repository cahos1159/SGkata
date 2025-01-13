package com.sgkata.bankaccount.infrastructure.out.adapter;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.domain.model.Transaction;
import com.sgkata.bankaccount.infrastructure.out.entity.AccountJpaEntity;
import com.sgkata.bankaccount.infrastructure.mapper.AccountMapper;
import com.sgkata.bankaccount.infrastructure.mapper.TransactionMapper;
import com.sgkata.bankaccount.infrastructure.out.repository.AccountRepository;
import com.sgkata.bankaccount.infrastructure.out.repository.TransactionRepository;
import org.hibernate.sql.exec.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountPersistenceAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountPersistenceAdapter accountPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Account account = new Account();
        account.setAccountId("12345");
        account.setOwner("John Doe");
        account.setBalance(new BigDecimal("1000.00"));

        AccountJpaEntity accountJpaEntity = new AccountJpaEntity();
        when(accountMapper.mapToAccountJpaEntity(account)).thenReturn(accountJpaEntity);

        // Act
        accountPersistenceAdapter.save(account);

        // Assert
        verify(accountRepository, times(1)).save(accountJpaEntity);
    }

    @Test
    void testGetAccountById() {
        // Arrange
        String accountId = "12345";
        AccountJpaEntity accountJpaEntity = new AccountJpaEntity();
        accountJpaEntity.setAccountId(accountId);
        accountJpaEntity.setOwner("John Doe");
        accountJpaEntity.setBalance(new BigDecimal("1000.00"));

        Account account = new Account();
        account.setAccountId(accountId);
        account.setOwner("John Doe");
        account.setBalance(new BigDecimal("1000.00"));

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountJpaEntity));
        when(accountMapper.mapToAccount(accountJpaEntity)).thenReturn(account);

        // Act
        Account result = accountPersistenceAdapter.getAccountById(accountId);

        // Assert
        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals("John Doe", result.getOwner());
        assertEquals(new BigDecimal("1000.00"), result.getBalance());
    }

    @Test
    void testGetAccountById_NotFound() {
        // Arrange
        String accountId = "12345";
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        ExecutionException exception = assertThrows(ExecutionException.class, () -> accountPersistenceAdapter.getAccountById(accountId));
        assertEquals("A problem occurred in the SQL executor : No Account with this ID found", exception.getMessage());
    }

    @Test
    void testLoadExistingTransactions() {
        // Arrange
        String accountId = "12345";
        List<Transaction> transactions = Collections.emptyList();
        when(transactionRepository.findAllTransaction(accountId)).thenReturn(Collections.emptyList());
        when(transactionMapper.mapListOfEntityToTransaction(Collections.emptyList())).thenReturn(transactions);

        // Act
        List<Transaction> result = accountPersistenceAdapter.loadExistingTransactions(accountId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
