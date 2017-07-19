package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 19.07.2017.
 */
public interface ScheduleRepository extends CrudRepository<Schedule,Long>{
    Schedule findById(String id);

    List<Schedule> findByService(Service service);

    List<Schedule> findByTime(String time);
}
