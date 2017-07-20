package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.services.ScheduleService;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.POST, value = "/schedule/edit")
    public ResponseEntity<String> editSchedule(@RequestBody Map<String,List<Object>> schedule){
        System.out.println(schedule.toString());
        return ResponseEntity.ok("ASd");
        //return scheduleService.addScheduleAll(schedule);
    }
}
