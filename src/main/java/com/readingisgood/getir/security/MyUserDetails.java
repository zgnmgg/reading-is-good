package com.readingisgood.getir.security;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.service.AccountQueryService;
import com.readingisgood.getir.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final AccountQueryService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final AccountEntity account = service.findByEmail(email);

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
