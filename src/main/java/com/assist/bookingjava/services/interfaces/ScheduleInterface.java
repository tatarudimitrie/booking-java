package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ScheduleInterface {

    ResponseEntity findAllSchedules();

    ResponseEntity findScheduleById(long id);

    ResponseEntity findScheduleByTime(String time);

    ResponseEntity findScheduleByService(Service service);

    ResponseEntity<String> editSchedule(Schedule schedule);

    ResponseEntity<String> addSchedule(Schedule schedule);

    ResponseEntity<String> addScheduleAll(List<Schedule> schedule);

    ResponseEntity<String> deleteSchedule(long id);
}
