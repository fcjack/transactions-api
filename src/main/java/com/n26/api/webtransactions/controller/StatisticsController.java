package com.n26.api.webtransactions.controller;

import com.n26.api.webtransactions.model.Statistics;
import com.n26.api.webtransactions.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Statistics getStatistics() {
        return new Statistics(statisticsService.getStatistics());
    }
}
