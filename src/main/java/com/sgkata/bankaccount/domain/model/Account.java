package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.AccountPersistence;

import java.math.BigDecimal;

public class Account {
    private String accountId;
    private String owner;
    private BigDecimal balance;

    public void save(AccountPersistence accountPersistance){
        accountPersistance.save(this);
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
