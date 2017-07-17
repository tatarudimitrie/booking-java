package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Recovery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecoveryRepository extends CrudRepository<Recovery, Long> {
    public List<Recovery> findByEmail(String email);
    public List<Recovery> findByResetToken(String resetToken);
   // public void saveRecovery(Recovery recovery);
}
