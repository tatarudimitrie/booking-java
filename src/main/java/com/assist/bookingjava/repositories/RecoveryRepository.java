package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Recovery;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecoveryRepository extends CrudRepository<Recovery, Long> {
    public Optional<Recovery> findByEmail(String email);
    public Optional<Recovery> findByResetToken(String resetToken);
   // public void saveRecovery(Recovery recovery);
}
