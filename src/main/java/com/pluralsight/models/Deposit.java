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
    private Long paymentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deposit_name")
    private String paymentName;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "deposit_date")
    private LocalDate paymentDate;

    @Column(name = "deposit_time")
    private LocalTime paymentTime;

    @Column(name = "deposit_amount")
    private BigDecimal paymentAmount;

    public Deposit() {
    }

    public Deposit(Long paymentId, Long userId,
                   String paymentName, String vendorName,
                   LocalDate paymentDate, LocalTime paymentTime,
                   BigDecimal paymentAmount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.paymentName = paymentName;
        this.vendorName = vendorName;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentAmount = paymentAmount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
