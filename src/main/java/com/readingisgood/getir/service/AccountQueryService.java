package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.AccountEntity;

public interface AccountQueryService {

    AccountEntity findByEmail(String email);
}
