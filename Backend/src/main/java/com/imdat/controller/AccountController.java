package com.imdat.controller;

import com.imdat.DTO.require.ChangePasswordReq;
import com.imdat.DTO.require.LoginReq;
import com.imdat.DTO.require.RegisterReq;
import com.imdat.DTO.respone.AdminUserDetailRes;
import com.imdat.DTO.respone.UserDetailRes;
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
    public void addNewAccount(@Valid @RequestBody RegisterReq registerPasswordRequest) {
        this.accountService.register(registerPasswordRequest);
    }

    @PostMapping("/auth/login")
    public String login(@Valid @RequestBody LoginReq request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        String token = jwtService.generateToken(request.getUsername());
        return  token;
    }

    @GetMapping("/admin/account_page")
    public ResponseEntity<Page<AdminUserDetailRes>> getSearchAccountByPage(
            @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(accountService.getAccount(inputSearch, direction, sortBy, page, size, role));
    }

    //Đổi mật khẩu (USER)
    @PutMapping("/user/account/changepassword")
    public void changePassword(@Valid @RequestBody ChangePasswordReq changePasswordRequest) {
        accountService.changePassword(changePasswordRequest);
    }

    //Lấy account theo Id (ADMIN)
    @GetMapping("/admin/account/{id}")
    public AdminUserDetailRes getAccountById(@PathVariable Integer id) {
        return accountService.getAdminUserAccountById(id);
    }

    @GetMapping("/admin/account")
    public List<AdminUserDetailRes> getAllAccount() {
        return accountService.getAllAccount();
    }
}
