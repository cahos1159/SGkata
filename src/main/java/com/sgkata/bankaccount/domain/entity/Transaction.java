package com.sgkata.bankaccount.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(String id, String accountNum, LocalDateTime date, BigDecimal amount) {
}
