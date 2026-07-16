package com.pluralsight.service;

import com.pluralsight.models.Payment;
import com.pluralsight.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getDepositsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    public boolean deleteByUserId(Long userId, Long paymentId) {
        if (!paymentRepository.existsByUserId(userId)) {
            return false;
        }
        if (!paymentRepository.existsById(paymentId)) {
            return false;
        }
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        paymentRepository.delete(payment);
        return true;
    }
}
