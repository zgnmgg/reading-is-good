package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.domain.repo.AccountRepository;
import com.readingisgood.getir.domain.request.SignUpRequest;
import com.readingisgood.getir.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    @Spy
    private DefaultAccountService defaultAccountService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private DefaultAccountQueryService defaultAccountQueryService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private static SignUpRequest signUpRequest;

    @BeforeEach
    public void init() {
        signUpRequest = SignUpRequest.builder()
                .name("Name")
                .email("Email")
                .password("password")
                .role(null)
                .build();
    }

    @Test()
    public void createProductTest_whenProductRequestComesIn_thenReturnSuccessProductResponse() {
        // GIVEN
        AccountEntity account = AccountEntity.builder()
                .name("Name")
                .email("Email")
                .password("password")
                .roles(null)
                .build();
        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(account);
        // WHEN
        AccountEntity accountResponse = defaultAccountService.singUp(signUpRequest);
        // THEN
        assertThat(accountResponse.getName()).isEqualTo("Name");
    }


    @Test()
    public void existsByUsername() {

        boolean isActive = this.accountRepository.existsAccountEntityByName("name");

        assertThat(isActive).isEqualTo(false);
    }


    @Test()
    public void existsByEmail() {

        boolean isActive = this.accountRepository.existsAccountEntityByEmail("name");

        assertThat(isActive).isEqualTo(false);
    }
}
