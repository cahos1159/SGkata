package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.TransactionPersistance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String id;
    private String accountNum;
    private LocalDateTime date;
    private BigDecimal amount;

    public void save( TransactionPersistance transactionPersistance) {
        transactionPersistance.save(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
