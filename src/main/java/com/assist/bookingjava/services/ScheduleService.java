package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.ScheduleRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ScheduleService implements ScheduleInterface{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ServiceRepository serviceRepository;


    public ResponseEntity findScheduleByService(Service service) {
        try {
            System.out.println(service.toString());
            Service currentService = serviceRepository.findById(service.getId());
            List<Schedule> scheduleList = scheduleRepository.findByService(currentService);
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addSchedule(Schedule schedule) {
        try {
            Service service = serviceRepository.findById(schedule.getService().getId());
            schedule.setService(service);
            scheduleRepository.save(schedule);
            System.out.println("Schedule was added, for service: " + service.toString());
            return ResponseEntity.ok("Schedule added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addScheduleAll(List<Schedule> schedule) {
        try {
            for(Schedule s : schedule) {
                Service service = serviceRepository.findById(s.getService().getId());
                s.setService(service);
                scheduleRepository.save(s);
                System.out.println("Schedule was added, for service: " + service.toString());
            }
            return ResponseEntity.ok("Schedule added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }
    public ResponseEntity<String> addScheduleTest(Map<String,List<Object>> ob)
    {
return ResponseEntity.ok("ASD");
    }
}
