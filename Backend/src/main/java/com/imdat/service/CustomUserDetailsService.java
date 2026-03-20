package com.imdat.service;

import com.imdat.entity.Account;
import com.imdat.repository.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountInterface accountInterface;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    return new User(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority(account.getRole())));
    }
}
