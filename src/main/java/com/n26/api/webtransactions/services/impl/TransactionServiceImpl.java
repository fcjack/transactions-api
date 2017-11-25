package com.n26.api.webtransactions.services.impl;

import com.n26.api.webtransactions.model.Transaction;
import com.n26.api.webtransactions.services.TransactionService;
import com.n26.api.webtransactions.storage.TransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionStorage transactionStorage;

    @Autowired
    public TransactionServiceImpl(TransactionStorage transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    @Override
    public void save(Transaction transaction) {
        transactionStorage.save(transaction);
    }
}
