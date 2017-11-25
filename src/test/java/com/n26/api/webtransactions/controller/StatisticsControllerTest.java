package com.n26.api.webtransactions.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.api.webtransactions.WebTransactionsApplicationTests;
import com.n26.api.webtransactions.model.Transaction;
import com.n26.api.webtransactions.services.StatisticsService;
import com.n26.api.webtransactions.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Slf4j
public class StatisticsControllerTest extends WebTransactionsApplicationTests {


    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TransactionService transactionService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void getStatisticsTest() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);

        Transaction transaction = new Transaction(30.0, zonedDateTime.toInstant().minusSeconds(30).toEpochMilli());
        transactionService.save(transaction);


        Transaction transaction2 = new Transaction(70.0, zonedDateTime.toInstant().minusSeconds(15).toEpochMilli());
        transactionService.save(transaction2);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getUpdatedStatistics() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);

        Transaction transaction = new Transaction(30.0, zonedDateTime.toInstant().minusSeconds(50).toEpochMilli());
        transactionService.save(transaction);


        Transaction transaction2 = new Transaction(70.0, zonedDateTime.toInstant().minusSeconds(15).toEpochMilli());
        transactionService.save(transaction2);

        log.info("Waiting 10 seconds to invalidate one transaction");
        Thread.sleep(10000);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("count", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("max", Matchers.is(70.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("min", Matchers.is(70.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("sum", Matchers.is(70.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("average", Matchers.is(70.0)))
                .andDo(MockMvcResultHandlers.print());
    }
}
