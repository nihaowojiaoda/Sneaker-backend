package com.imdat.controller;

import com.imdat.DTO.ChangePasswordRequest;
import com.imdat.DTO.LoginRequest;
import com.imdat.DTO.RegisterPasswordRequest;
import com.imdat.entity.Account;
import com.imdat.security.JwtService;
import com.imdat.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @DeleteMapping("/admin/account/{id}")
    public void deleteAccountById(@PathVariable Integer id) {
        this.accountService.removeAccountById(id);
    }


    @PostMapping("/auth/register")
    public void addNewAccount(@Valid @RequestBody RegisterPasswordRequest registerPasswordRequest) {
        this.accountService.register(registerPasswordRequest);
    }


    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        String token = jwtService.generateToken(request.getUsername());
        

        return ResponseEntity.ok(Map.of("token", token));
    }


    @GetMapping("/admin/account")
    public ResponseEntity<Page<Account>> getSearchAccountByPage(
            @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(accountService.getAccount(inputSearch, direction, sortBy, page, size, role));
    }


    @PutMapping("/user/account/changepassword")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        accountService.changePassword(changePasswordRequest);
    }


    @GetMapping("/admin/account/{id}")
    public Account getAccountById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }
}
