package com.papl.demo.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SchedulerImpl {

    @Autowired
    NightDrivingSvcImpl extraShiftSvc;

    @Scheduled(cron = "0 */5 * * * *") // Runs every 5 minutes
    public void performScheduledTask() {
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(10, 25); // 11 PM
        LocalTime end = LocalTime.of(6, 0);    // 6 AM

        if (now.isAfter(start) || now.isBefore(end)) {
            extraShiftSvc.nightDriving();
            System.out.println("Task executed at: " + now);
        } else {
            System.out.println("Task skipped at: " + now);
        }
    }
}
