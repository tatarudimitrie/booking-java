package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(method = RequestMethod.GET, value="/schedules/all")
    public ResponseEntity findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @RequestMapping(method = RequestMethod.GET, value="/schedules/id/{id}")
    public ResponseEntity findScheduleById(@PathVariable long id) {
        return scheduleService.findScheduleById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/schedules/time/{time}")
    public ResponseEntity findScheduleByTime(@PathVariable String time) {
        return scheduleService.findScheduleByTime(time);
    }

    @RequestMapping(method = RequestMethod.POST, value="/schedules/service")
    public ResponseEntity findScheduleByService(@RequestBody Service service) {
        return scheduleService.findScheduleByService(service);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/schedules/edit")
    public ResponseEntity<String> editSchedule(@RequestBody Schedule schedule){
        return scheduleService.editSchedule(schedule);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/schedules/add")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule){
        return scheduleService.addSchedule(schedule);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/schedules/add/all")
    public ResponseEntity<String> addScheduleAll(@RequestBody List<Schedule> schedule){
        return scheduleService.addScheduleAll(schedule);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/schedules/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable long id){
        return scheduleService.deleteSchedule(id);
    }
}
