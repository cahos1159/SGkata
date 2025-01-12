package com.sgkata.bankaccount.domain.port;

import com.sgkata.bankaccount.domain.model.Transaction;

public interface TransactionPersistance {

    void save(Transaction transaction);


}
