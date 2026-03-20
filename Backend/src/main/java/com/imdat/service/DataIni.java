package com.imdat.service;

import com.imdat.entity.Account;
import com.imdat.repository.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataIni implements CommandLineRunner {
    @Autowired
    AccountInterface accountInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Account acc = new Account();
        acc.setUsername("taolaadmin");
        acc.setPassword(passwordEncoder.encode("123123123Dts!")); // Lưu ý nên encode password
        acc.setEmail("taolaadmin@example.com");
        acc.setRole("ROLE_ADMIN");
        acc.setPhoneNumber("0353250191");
        acc.setActive(true);

        accountInterface.save(acc);

        System.out.println("Account added: " + acc.getUsername());
    }
}
