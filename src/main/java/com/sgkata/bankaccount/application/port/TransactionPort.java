package com.sgkata.bankaccount.application.port;

import com.sgkata.bankaccount.application.dto.TransactionDto;

public interface TransactionPort {
    void newTransaction(TransactionDto transactionDto);
}
