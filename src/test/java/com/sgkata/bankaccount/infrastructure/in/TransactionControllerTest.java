package com.sgkata.bankaccount.infrastructure.in;

import com.sgkata.bankaccount.application.port.TransactionPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionPort transactionPort;


    @Test
    void sendMoney_shouldCallTransactionPortWithCorrectTransactionDto() throws Exception {
        // Arrange
        String sourceAccountId = "source123";
        String targetAccountId = "target456";
        String amount = "100.50";

        // Act
        mockMvc.perform(post("/accounts/send/" + sourceAccountId + "/" + targetAccountId + "/" + amount))
                .andExpect(status().isOk());

        // Assert
        verify(transactionPort, times(1)).newTransaction(Mockito.argThat(transaction ->
                transaction.sourceAccount().equals(sourceAccountId) &&
                        transaction.targetAccount().equals(targetAccountId) &&
                        transaction.amount().compareTo(new BigDecimal(amount)) == 0 &&
                        transaction.date() != null
        ));
    }
}

