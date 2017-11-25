package com.n26.api.webtransactions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.api.webtransactions.model.Transaction;
import com.n26.api.webtransactions.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("transaction")
public class TransactionController {


    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveTransaction(@Valid @RequestBody Transaction transaction) throws JsonProcessingException {
        transactionService.save(transaction);
    }

}
