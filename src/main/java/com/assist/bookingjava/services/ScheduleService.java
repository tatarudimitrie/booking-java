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

        System.out.println("####  ID:  " + service.getId() + "  ####");

        try {
            System.out.println(service.toString());
            Service currentService = serviceRepository.findById((long) service.getId());
            List<Schedule> scheduleList = scheduleRepository.findByService(currentService);

            for(Schedule s: scheduleList) {
                s.setService(null);
            }

            System.out.println("RESPONSE: " + scheduleList.toString());
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            System.out.println("Am trimis eroare!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addSchedule(Schedule schedule) {

        System.out.println(schedule.toString());
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

        System.out.println(schedule.toString());
        String sanitize = scheduleSanitization(schedule);
        if (!sanitize.equals("")) {
            return ResponseEntity.badRequest().body("Eroare la introducere "+sanitize);
        }
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
    private String scheduleSanitization(List<Schedule> schedule)
    {
        String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String error="";

            for(Schedule s : schedule) {
                String schedul=s.getTime();
                if(schedul.length()>5 || schedul.length()<3)
                {
                    error+="Eroare la lungime";
                }
                if (!allowed.contains(schedul)) {
                 //   error += "Error  " + schedul + " ";
                }

            }
return error;
    }
}
