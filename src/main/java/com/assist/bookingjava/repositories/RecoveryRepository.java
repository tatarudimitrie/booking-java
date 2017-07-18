package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Recovery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecoveryRepository extends CrudRepository<Recovery, Long> {
    List<Recovery> findByEmail(String email);
    Recovery findByResetToken(String resetToken);
}
