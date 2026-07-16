package com.pluralsight.repository;

import com.pluralsight.models.Deposit;
import com.pluralsight.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
