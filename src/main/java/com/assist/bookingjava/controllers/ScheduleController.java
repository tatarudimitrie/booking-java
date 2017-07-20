package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(method = RequestMethod.POST, value = "/schedule/add")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule){
        return scheduleService.addSchedule(schedule);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/schedule/add/all")
    public ResponseEntity<String> addScheduleAll(@RequestBody List<Schedule> schedule){
        return scheduleService.addScheduleAll(schedule);
    }
}