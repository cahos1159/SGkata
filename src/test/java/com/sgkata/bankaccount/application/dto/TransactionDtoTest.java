package com.sgkata.bankaccount.application.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTransactionDto() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "source123",
                "target456",
                LocalDateTime.now(),
                new BigDecimal("100.00")
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertTrue(violations.isEmpty(), "Expected no validation violations");
    }

    @Test
    void testInvalidSourceAccountId() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "src",
                "target456",
                LocalDateTime.now(),
                new BigDecimal("100.00")
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertFalse(violations.isEmpty(), "Expected validation violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("sourceAccount must be between 6 and 200 characters")));
    }

    @Test
    void testInvalidTargetAccountId() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "source123",
                "tgt",
                LocalDateTime.now(),
                new BigDecimal("100.00")
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertFalse(violations.isEmpty(), "Expected validation violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("targetAccount must be between 6 and 200 characters")));
    }

    @Test
    void testNullDate() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "source123",
                "target456",
                null,
                new BigDecimal("100.00")
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertFalse(violations.isEmpty(), "Expected validation violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("date cannot be null")));
    }

    @Test
    void testNegativeAmount() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "source123",
                "target456",
                LocalDateTime.now(),
                new BigDecimal("-100.00")
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertFalse(violations.isEmpty(), "Expected validation violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be greater than or equal to 0")));
    }

    @Test
    void testNullAmount() {
        // Arrange
        TransactionDto transaction = new TransactionDto(
                "12345",
                "source123",
                "target456",
                LocalDateTime.now(),
                null
        );

        // Act
        Set<ConstraintViolation<TransactionDto>> violations = validator.validate(transaction);

        // Assert
        assertFalse(violations.isEmpty(), "Expected validation violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("amount cannot be null")));
    }
}
