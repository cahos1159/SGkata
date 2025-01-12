package com.sgkata.bankaccount.domain.entity;

import java.math.BigDecimal;
import java.util.List;

public record Account(String accountNum, String owner, BigDecimal balance, List<Transaction> transactions) {

}
