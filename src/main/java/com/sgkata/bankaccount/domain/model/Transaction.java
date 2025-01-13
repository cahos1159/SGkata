package com.sgkata.bankaccount.domain.model;

import com.sgkata.bankaccount.domain.port.TransactionPersistance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String id;
    private String sourceAccount;
    private String targetAccount;
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

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
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
