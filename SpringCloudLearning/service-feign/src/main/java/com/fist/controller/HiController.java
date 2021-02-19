package com.fist.controller;

import com.fist.web.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    private ScheduleServiceHi scheduleServiceHi;

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
