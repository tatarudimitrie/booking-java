package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.ScheduleRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Created by root on 19.07.2017.
 */
public class ScheduleService implements ScheduleInterface{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ServiceRepository serviceRepository;


    public ResponseEntity findServiceByCompany(Service service) {
        try {
            System.out.println(service.toString());
            Service currentService = serviceRepository.findById(service.getId());
            List<Schedule> scheduleList = scheduleRepository.findByService((org.springframework.stereotype.Service) currentService);
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addService(Schedule schedule) {
        try {
            Service service = serviceRepository.findById(schedule.getService().getId());
            schedule.setService(service);
            scheduleRepository.save(schedule);
            System.out.println("Schedule was added, for company: " + service.toString());
            return ResponseEntity.ok("Schedule added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }


}
