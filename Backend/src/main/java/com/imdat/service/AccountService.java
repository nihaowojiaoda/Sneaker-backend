package com.imdat.service;

import com.imdat.DTO.AdminUserDetailDTO;
import com.imdat.DTO.ChangePasswordRequest;
import com.imdat.DTO.RegisterPasswordRequest;
import com.imdat.DTO.UserDetailDTO;
import com.imdat.entity.Account;
import com.imdat.entity.Cart;
import com.imdat.repository.AccountInterface;
import com.imdat.specification.AccountSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {
    @Autowired
    private AccountInterface accountInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountSpecification accountSpecification;

    public void register(RegisterPasswordRequest registerPasswordRequest) {
        String hPassword = passwordEncoder.encode(registerPasswordRequest.getPassword());
        Account nAccount = new Account(
                registerPasswordRequest.getUsername(),
                hPassword,
                registerPasswordRequest.getEmail(),
                registerPasswordRequest.getPhoneNumber(),
                registerPasswordRequest.getAddress()
        );

        Cart cart = new Cart(nAccount);

        accountInterface.save(nAccount);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        com.imdat.entity.Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), account.getPassword())) {
            throw new RuntimeException("Wrong current password");
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new RuntimeException("Confirm password incorect");
        }

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), account.getPassword())) {
            throw new RuntimeException("New Password can not same as current password");
        }

        account.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    }

    public void removeAccountById(Integer id) {
        this.accountInterface.deleteById(id);
    }

    public UserDetailDTO getUserAccountProfileById(Integer id) {
        Account account = accountInterface.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return new UserDetailDTO(
                account.getUsername(),
                account.getAddress(),
                account.getPhoneNumber(),
                account.getEmail()
        );
    }

    public AdminUserDetailDTO getAdminUserAccountProfileById(Integer id) {
        Account account = accountInterface.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return new AdminUserDetailDTO(
                account.getUsername(),
                account.getPhoneNumber(),
                account.getAddress(),
                account.getEmail(),
                account.getRole(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    public List<Account> getAdminAllUser() {
        return accountInterface.findAll();
    }

    @Transactional
    public void modifyActiveAccountById(boolean flag, Integer id) {
        Account account = accountInterface.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setActive(flag);
    }

    public Page<Account> getAccount(String inputSearch, String direction, String sortBy, Integer page, Integer size, String role) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Account> spec = Specification.where(AccountSpecification.hasUsernameEmailPhoneNumber(inputSearch))
                .and(AccountSpecification.hasRole(role));

        return accountInterface.findAll(spec, pageable);
    }

    public Account getAccountById(Integer id) {
        return accountInterface.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<Account> getAllAccount() {
        return accountInterface.findAll();
    }
}
