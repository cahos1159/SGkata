package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.AccountPersistance;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private String accountId;
    private String owner;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public void save(AccountPersistance accountPersistance){
        accountPersistance.save(this);
    }

    public void loadExistingTransaction(AccountPersistance accountPersistance) {
        setTransactions(accountPersistance.loadExistingTransactions(this.accountId));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountNum) {
        this.accountId = accountNum;
    }
}
