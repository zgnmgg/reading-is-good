package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.domain.request.SignInRequest;
import com.readingisgood.getir.domain.request.SignUpRequest;
import com.readingisgood.getir.domain.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;

public interface AccountService {

    AccountEntity singUp(SignUpRequest signUpRequest);

    LoginResponse signIn(SignInRequest signInRequest);

    AccountEntity getIdentityFromToken(HttpServletRequest httpServletRequest);
}
