package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.domain.repo.AccountRepository;
import com.readingisgood.getir.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultAccountQueryService implements AccountQueryService{

    private final AccountRepository repository;

    @Override
    public AccountEntity findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }
}
