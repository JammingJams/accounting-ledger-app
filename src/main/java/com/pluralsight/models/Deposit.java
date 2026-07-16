package com.pluralsight.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id")
    private Long depositId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deposit_name")
    private String depositName;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "deposit_date")
    private LocalDate depositDate;

    @Column(name = "deposit_time")
    private LocalTime depositTime;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    public Deposit() {
    }

    public Deposit(Long depositId, Long userId,
                   String depositName, String vendorName,
                   LocalDate depositDate, LocalTime depositTime,
                   BigDecimal depositAmount) {
        this.depositId = depositId;
        this.userId = userId;
        this.depositName = depositName;
        this.vendorName = vendorName;
        this.depositDate = depositDate;
        this.depositTime = depositTime;
        this.depositAmount = depositAmount;
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public LocalTime getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(LocalTime depositTime) {
        this.depositTime = depositTime;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
}
