package com.n26.api.webtransactions.services.impl;

import com.n26.api.webtransactions.services.StatisticsService;
import com.n26.api.webtransactions.storage.TransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransactionStorage transactionStorage;


    @Override
    public DoubleSummaryStatistics getStatistics() {
        return transactionStorage.getStatistics();
    }

}
