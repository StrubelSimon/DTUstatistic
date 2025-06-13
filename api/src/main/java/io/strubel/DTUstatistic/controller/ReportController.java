package io.strubel.DTUstatistic.controller;

import io.strubel.DTUstatistic.dto.InverterSummary;
import io.strubel.DTUstatistic.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/daily") // day+month+year
    public Map<String, InverterSummary> getDailyReport(@RequestParam String day) {
        return reportService.getDailyReport(day);
    }

    @GetMapping("/monthly") // month+year
    public Map<String, InverterSummary> getMonthlyReport(@RequestParam String month) {
        return reportService.getMonthlyReport(month);
    }

    @GetMapping("/yearly") // year
    public Map<String, InverterSummary> getYearlyReport(@RequestParam String year) {
        return reportService.getYearlyReport(year);
    }

}
