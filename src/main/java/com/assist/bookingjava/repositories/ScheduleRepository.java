package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule,Long>{
    Schedule findById(String id);
    List<Schedule> findByService(Service service);
    List<Schedule> findByTime(String time);
}