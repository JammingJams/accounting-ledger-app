package com.pluralsight.controllers;

import com.pluralsight.models.Deposit;
import com.pluralsight.models.User;
import com.pluralsight.service.DepositService;
import com.pluralsight.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/deposits")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DepositController {

    private final DepositService depositService;
    private final UserService userService;

    public DepositController(DepositService depositService,
                             UserService userService) {
        this.depositService = depositService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<Deposit>> getDepositByUserId(Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (depositService.getDepositsByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(depositService.getDepositsByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<Deposit> addDeposit(@RequestBody Deposit deposit, Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (userId == null) {
            return ResponseEntity.notFound().build();
        }

        deposit.setUserId(userId);
        Deposit saved = depositService.create(deposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("{depositId}")
    public ResponseEntity<Void> deleteDepositByUserId(@PathVariable Long depositId, Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (depositService.deleteByUserId(userId, depositId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
