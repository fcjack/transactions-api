package com.n26.api.webtransactions.model;

import lombok.Getter;
import lombok.Setter;

import java.util.DoubleSummaryStatistics;

@Getter
@Setter
public class Statistics {

    private Double sum;
    private Double min;
    private Double max;
    private Double avg;
    private Long count;


    public Statistics(DoubleSummaryStatistics doubleSummaryStatistics) {
        setAvg(doubleSummaryStatistics.getAverage());
        setMax(doubleSummaryStatistics.getMax());
        setMin(doubleSummaryStatistics.getMin());
        setSum(doubleSummaryStatistics.getSum());
        setCount(doubleSummaryStatistics.getCount());
    }
}
