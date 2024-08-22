package com.papl.demo.Controller;

import com.papl.demo.ServiceImpl.NightDrivingSvcImpl;
import com.papl.demo.ServiceImpl.SchedulerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NightDrivingController {

    @Autowired
    NightDrivingSvcImpl serviceImpl;

    private final SchedulerImpl taskSchedulerService;

    public NightDrivingController(SchedulerImpl taskSchedulerService) {
        this.taskSchedulerService = taskSchedulerService;
    }

    @GetMapping("/nightDriving")
    public ResponseEntity<Void> nightShift(){
        //serviceImpl.nightDriving();
        taskSchedulerService.performScheduledTask();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
