package com.imdat.controller;

import com.imdat.DTO.respone.StatisticRes;
import com.imdat.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/admin/statistic")
    public StatisticRes getStatistic() {
        return statisticService.getStatistic();
    }
}
