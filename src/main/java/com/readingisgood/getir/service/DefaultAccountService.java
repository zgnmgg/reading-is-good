package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.domain.repo.AccountRepository;
import com.readingisgood.getir.domain.request.SignInRequest;
import com.readingisgood.getir.domain.request.SignUpRequest;
import com.readingisgood.getir.domain.response.LoginResponse;
import com.readingisgood.getir.exception.ExistsException;
import com.readingisgood.getir.exception.ForbiddenException;
import com.readingisgood.getir.exception.NotFoundException;
import com.readingisgood.getir.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultAccountService implements AccountService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository repository;
    private final AccountQueryService accountQueryService;

    @Override
    public AccountEntity singUp(SignUpRequest signUpRequest) {
        if (this.existsByUsername(signUpRequest.getName())) {
            throw new ExistsException();
        }
        if (this.existsByEmail(signUpRequest.getEmail())) {
            throw new ExistsException();
        }
        final var account = AccountEntity.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(signUpRequest.getRole())
                .name(signUpRequest.getName())
                .build();

        log.debug("New Account record to save: {}", account);
        this.repository.save(account);

        return account;
    }

    @Override
    public LoginResponse signIn(SignInRequest signInRequest) {
        AccountEntity account = this.findByEmail(signInRequest.getEmail());
        if (! passwordEncoder.matches(signInRequest.getPassword(), account.getPassword())) {
            throw new ForbiddenException();
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        return new LoginResponse(
                jwtTokenProvider.createToken(signInRequest.getEmail(), this.findByEmail(signInRequest.getEmail()).getRoles()),
                "refreshToken"
        );
    }

    private boolean existsByUsername(String username) {
        return this.repository.existsAccountEntityByName(username);
    }


    private boolean existsByEmail(String email) {
        return this.repository.existsAccountEntityByEmail(email);
    }

    public AccountEntity findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    @Override
    public AccountEntity getIdentityFromToken(HttpServletRequest httpServletRequest) {
        return accountQueryService.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpServletRequest)));
    }
}
