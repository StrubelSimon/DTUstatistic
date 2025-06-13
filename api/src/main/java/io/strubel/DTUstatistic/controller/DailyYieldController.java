package io.strubel.DTUstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.strubel.DTUstatistic.model.DailyYield;
import io.strubel.DTUstatistic.service.DailyYieldService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data")
public class DailyYieldController {

    @Autowired
    private DailyYieldService dailyYieldService;

    @GetMapping("/daily") // day+month+year
    public Optional<DailyYield> getDailyData(@RequestParam String day) {
        return dailyYieldService.getDailyData(day);
    }

    @GetMapping("/monthly") // month+year
    public List<DailyYield> getMonthlyData(@RequestParam String month) {
        return dailyYieldService.getMonthlyData(month);
    }

    @GetMapping("/yearly") // year
    public List<DailyYield> getYearlyData(@RequestParam String year) {
        return dailyYieldService.getYearlyData(year);
    }

}