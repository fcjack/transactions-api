package com.n26.api.webtransactions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.api.webtransactions.WebTransactionsApplicationTests;
import com.n26.api.webtransactions.model.Transaction;
import com.n26.api.webtransactions.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;


public class TransactionControllerTest extends WebTransactionsApplicationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TransactionService transactionService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void validRequestTest() throws Exception {
        Transaction transaction = new Transaction();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 10; i++) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
            transaction.setAmount(ThreadLocalRandom.current().nextDouble());
            transaction.setTimestamp(zonedDateTime.toInstant().minusSeconds(30 - i).toEpochMilli());

            mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaction)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));

            Mockito.verify(transactionService).save(transaction);
        }
    }

    @Test
    public void rejectRequestTest() throws Exception {
        Transaction transaction = new Transaction();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        transaction.setTimestamp(zonedDateTime.toInstant().minusSeconds(30).toEpochMilli());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));


        transaction.setAmount(12.3);
        transaction.setTimestamp(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));
    }

    @Test
    public void handleInvalidTimestampTest() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(1478192204000l);
        transaction.setAmount(12.3);

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));
    }
}
