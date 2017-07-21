package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.ScheduleRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ScheduleService implements ScheduleInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    private String nl = "\n";

    public ResponseEntity findAllSchedules() {
        System.out.println(nl + "SCHEDULE GET: /schedules/all");

        try {
            List<Schedule> scheduleList = new ArrayList<>();

            for (Schedule s : scheduleRepository.findAll()) {
                scheduleList.add(s);
            }

            System.out.println("OK: " + scheduleList.toString());
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findScheduleById(long id) {
        System.out.println(nl + "SCHEDULE GET: /schedules/id/{" + id + "}");

        try {
            Schedule schedule = scheduleRepository.findOne(id);

            System.out.println("OK: " + schedule.toString());
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findScheduleByTime(String time) {
        System.out.println(nl + "SCHEDULE GET: /schedules/time/{" + time + "}");

        try {
            List<Schedule> scheduleList = scheduleRepository.findByTime(time);

            System.out.println("OK: " + scheduleList.toString());
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findScheduleByService(Service service) {
        System.out.println(nl + "SCHEDULE (GET) POST: /schedules/service for " + service.toString());

        try {
            Service currentService = serviceRepository.findById(service.getId());
            List<Schedule> scheduleList = scheduleRepository.findByService(currentService);

            for(Schedule s: scheduleList) {
                s.setService(null);
            }

            System.out.println("OK: " + scheduleList.toString());
            return ResponseEntity.ok(scheduleList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editSchedule(Schedule schedule) {
        System.out.println(nl + "SCHEDULE PUT: /schedules/edit - for " + schedule.toString());

        try {
            Schedule currentSchedule = scheduleRepository.findOne(schedule.getId());
            currentSchedule.setTime(schedule.getTime());
            scheduleRepository.save(currentSchedule);

            System.out.println("OK: " + currentSchedule.toString());
            return ResponseEntity.ok("Schedule was successfully edited!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addSchedule(Schedule schedule) {
        System.out.println(nl + "SCHEDULE POST: /schedules/add - for " + schedule.toString());

        try {
            Service service = serviceRepository.findById(schedule.getService().getId());
            schedule.setService(service);
            scheduleRepository.save(schedule);
            long id = schedule.getId();

            System.out.println("OK: ID: " + id + " | " + service.toString() + " was added for" + schedule.toString());
            return ResponseEntity.ok("Schedule added successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addScheduleAll(List<Schedule> schedule) {
        System.out.println(nl + "SCHEDULE POST ALL: /schedules/add/all - for " + schedule.toString());

        if (!scheduleSanitization(schedule)) {
            return ResponseEntity.badRequest().body("Wrong input");
        }

        try {
            for(Schedule s : schedule) {
                Service service = serviceRepository.findById(s.getService().getId());
                s.setService(service);
                scheduleRepository.save(s);
                System.out.println("Schedule " + s.toString() + " was added, for service: " + service.toString());
            }
            return ResponseEntity.ok("Schedule added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteSchedule(long id) {
        System.out.println(nl + "SCHEDULE DELETE: /schedules/delete - for schedule with id " + id );

        try {
            scheduleRepository.delete(id);

            System.out.println("OK: Deleted!");
            return ResponseEntity.ok("Schedule with id " + id + " was successfully deleted!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    private boolean scheduleSanitization(List<Schedule> schedule)
    {
        String error = "";

        for(Schedule s : schedule) {
            String scheduleString =s.getTime();
            if(scheduleString.length()>5 || scheduleString.length()<3) {
                error += "Length error ";
            }
        }

        return (error.equals(""));
    }
}
