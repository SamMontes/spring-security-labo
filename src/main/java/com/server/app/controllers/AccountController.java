package com.server.app.controllers;

import com.server.app.dto.account.AccountRequest;
import com.server.app.dto.account.AccountResponse;
import com.server.app.services.AccountService;
import com.server.app.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/cuentas")
    public ResponseEntity<List<AccountResponse>> getAccount() {
        return ResponseEntity.ok(accountService.getAccount());
    }

    @PostMapping("/cuentas")
    public ResponseEntity<AccountResponse> createCuenta(@Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
