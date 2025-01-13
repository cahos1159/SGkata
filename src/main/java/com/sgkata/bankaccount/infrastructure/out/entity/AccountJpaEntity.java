package com.sgkata.bankaccount.infrastructure.out.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class AccountJpaEntity {

    @Id
    @GeneratedValue
    @NotNull
    private String accountId;
    @NotNull
    private String owner;
    @NotNull
    private BigDecimal balance;

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
