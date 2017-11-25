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

        setSum(doubleSummaryStatistics.getSum());
        setCount(doubleSummaryStatistics.getCount());

        if (Double.isInfinite(doubleSummaryStatistics.getMax()) || Double.isNaN(doubleSummaryStatistics.getMax())) {
            setMax(0.0);
        } else {
            setMax(doubleSummaryStatistics.getMax());
        }

        if (Double.isInfinite(doubleSummaryStatistics.getMin()) || Double.isNaN(doubleSummaryStatistics.getMin())) {
            setMin(0.0);
        } else {
            setMin(doubleSummaryStatistics.getMax());
        }
    }
}
