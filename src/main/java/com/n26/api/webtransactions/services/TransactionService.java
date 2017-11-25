package com.n26.api.webtransactions.services;

import com.n26.api.webtransactions.model.Transaction;

public interface TransactionService {

    void save(Transaction transaction);
}
