package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.repositories.RecoveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("recoveryServices")
public class RecoveryService {
    @Autowired
    private RecoveryRepository recoveryRepository;

    public List<Recovery> findByEmail(String email) {
        return recoveryRepository.findByEmail(email);
    }

    public Recovery findByResetToken(String resetToken) {
        return recoveryRepository.findByResetToken(resetToken);
    }

    public void saveRecovery(Recovery recovery) {
        recoveryRepository.save(recovery);
    }

    public String deleteRecovery(long id) {
        recoveryRepository.delete(id);
        return "DELETE: Success!";
    }
}
