package com.n26.api.webtransactions.storage;

import com.n26.api.webtransactions.model.Transaction;

import java.util.DoubleSummaryStatistics;

public interface TransactionStorage {

    void save(Transaction transaction);

    DoubleSummaryStatistics getStatistics();
}
