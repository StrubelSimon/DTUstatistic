package io.strubel.DTUstatistic.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.strubel.DTUstatistic.service.DailyYieldService;

@Component
public class DailyYieldScheduler {

    @Autowired
    private DailyYieldService dailyYieldService;

    @Scheduled(cron = "*/10 * * * * *")
    public void runHourly() {
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                + "] CronJob - DailyYield");
        dailyYieldService.fetchAndUpdateDailyData();
    }
}
