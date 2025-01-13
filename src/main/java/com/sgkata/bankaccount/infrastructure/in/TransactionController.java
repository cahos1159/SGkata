package com.sgkata.bankaccount.infrastructure.in;

import com.sgkata.bankaccount.application.dto.TransactionDto;
import com.sgkata.bankaccount.application.port.TransactionPort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@RestController
public class TransactionController {

    private final TransactionPort transactionPort;

    public TransactionController(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
            @PathVariable("sourceAccountId") String sourceAccountId,
            @PathVariable("targetAccountId") String targetAccountId,
            @PathVariable("amount") BigDecimal amount) {

        final TransactionDto transaction = new TransactionDto(
                null,
                sourceAccountId,
                targetAccountId,
                LocalDateTime.now(),
                amount);

        transactionPort.newTransaction(transaction);
    }

}
