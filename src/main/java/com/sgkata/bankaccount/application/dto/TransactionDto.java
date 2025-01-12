package com.sgkata.bankaccount.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        String id,
        @NotNull(message = "sourceAccountId cannot be null")
        @Size(min = 6, max = 12, message = "sourceAccount must be between 6 and 200 characters")
        String sourceAccount,
        @NotNull(message = "targetAccountId cannot be null")
        @Size(min = 6, max = 12, message = "targetAccount must be between 6 and 200 characters")
        String targetAccount,
        @NotNull(message = "date cannot be null") LocalDateTime date,
        @NotNull(message = "amount cannot be null")
        @PositiveOrZero(message = "amount must be greater than or equal to 0")
        BigDecimal amount
) {}

