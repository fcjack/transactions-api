package com.n26.api.webtransactions.storage.impl;

import com.n26.api.webtransactions.model.Transaction;
import com.n26.api.webtransactions.storage.TransactionStorage;
import com.n26.api.webtransactions.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class TransactionStorageImpl implements TransactionStorage {


    private static final long MAX_DURATION = 60;
    private ConcurrentLinkedQueue<Transaction> transactions;
    private AtomicReference<DoubleSummaryStatistics> atomicStatistics;
    private ExecutorService executor;


    @PostConstruct
    public void initialize() {
        this.transactions = new ConcurrentLinkedQueue<>();
        this.atomicStatistics = new AtomicReference<>();
        this.executor = Executors.newFixedThreadPool(5);
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
        Runnable task = this::updateStatistics;
        Runnable sortTaks = this::sortQueue;
        executor.submit(task);
        executor.submit(sortTaks);
    }

    private void sortQueue() {
        transactions = transactions.stream()
                .sorted(Comparator.comparingLong(Transaction::getTimestamp))
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }

    private void updateStatistics() {
        atomicStatistics.updateAndGet(doubleSummaryStatistics1 -> transactions.stream()
                .collect(Collectors.summarizingDouble(Transaction::getAmount)));
    }

    @Override
    public DoubleSummaryStatistics getStatistics() {
        return atomicStatistics.get();
    }

    @Scheduled(fixedRate = 1000)
    public void removeOldValues() {
        Transaction head = transactions.peek();
        while (head != null && DateUtil.olderThenLimit(head.getTimestamp(), ChronoUnit.SECONDS, MAX_DURATION)) {
            transactions.poll();
            head = transactions.peek();
        }

        atomicStatistics.updateAndGet(doubleSummaryStatistics1 -> transactions.stream()
                .collect(Collectors.summarizingDouble(Transaction::getAmount)));
    }

}
