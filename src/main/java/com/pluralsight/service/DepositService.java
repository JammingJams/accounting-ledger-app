package com.pluralsight.service;

import com.pluralsight.models.Deposit;
import com.pluralsight.repository.DepositRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<Deposit> search(String depositName, String vendorName,
                                LocalTime minTime, LocalTime maxTime,
                                LocalDate minDate, LocalDate maxDate,
                                BigDecimal minAmount, BigDecimal maxAmount) {
        return depositRepository.findAll().stream()
                .filter(d -> depositName == null  || d.getDepositName().contains(depositName))
                .filter(d -> vendorName == null || d.getVendorName().contains(vendorName))
                .filter(d -> minTime == null || d.getDepositTime().isBefore(minTime))
                .filter(d -> maxTime == null || d.getDepositTime().isAfter(maxTime))
                .filter(d -> minDate == null || d.getDepositDate().isBefore(minDate))
                .filter(d -> maxDate == null || d.getDepositDate().isAfter(maxDate))
                .filter(d -> minAmount == null || d.getDepositAmount().compareTo(minAmount) > 0)
                .filter(d -> maxAmount == null || d.getDepositAmount().compareTo(maxAmount) < 0)
                .toList();
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
