package com.pluralsight.repository;

import com.pluralsight.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
