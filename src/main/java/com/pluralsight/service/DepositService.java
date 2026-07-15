package com.pluralsight.service;

import com.pluralsight.models.Deposit;
import com.pluralsight.repository.DepositRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<Deposit> getDepositsByUserId(Long userId) {
        return depositRepository.findByUserId(userId);
    }

    public Deposit create(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public boolean deleteByUserId(Long userId, Long depositId) {
        if (!depositRepository.existsByUserId(userId)) {
            return false;
        }
        if (!depositRepository.existsById(depositId)) {
            return false;
        }
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        depositRepository.delete(deposit);
        return true;
    }
}
