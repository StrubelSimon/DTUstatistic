package io.strubel.DTUstatistic.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.strubel.DTUstatistic.service.InverterService;

@Component
public class InverterScheduler {

    @Autowired
    private InverterService inverterService;

    @Scheduled(cron = "0 5 * * * *")
    public void runHourly() {
        System.out.println("CronJob - Inverter");
        inverterService.fetchAndSaveInverters();
    }
}
