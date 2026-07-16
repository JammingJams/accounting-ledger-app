package com.pluralsight.controllers;

import com.pluralsight.models.Payment;
import com.pluralsight.models.User;
import com.pluralsight.service.PaymentService;
import com.pluralsight.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService,
                             UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<Payment>> getDepositByUserId(Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (paymentService.getDepositsByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(paymentService.getDepositsByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<Payment> addDeposit(@RequestBody Payment payment, Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (userId == null) {
            return ResponseEntity.notFound().build();
        }

        payment.setUserId(userId);
        Payment saved = paymentService.create(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("{paymentId}")
    public ResponseEntity<Void> deleteDepositByUserId(@PathVariable Long paymentId, Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (paymentService.deleteByUserId(userId, paymentId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
