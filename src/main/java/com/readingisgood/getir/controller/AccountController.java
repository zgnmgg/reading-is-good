package com.readingisgood.getir.controller;

import com.readingisgood.getir.domain.model.AccountEntity;
import com.readingisgood.getir.domain.request.SignInRequest;
import com.readingisgood.getir.domain.request.SignUpRequest;
import com.readingisgood.getir.domain.response.LoginResponse;
import com.readingisgood.getir.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create user",
            notes = "This method creates a new user for authenticate API's",
            response = AccountEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AccountEntity.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    public ResponseEntity<AccountEntity> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return new ResponseEntity<>(service.singUp(signUpRequest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login user",
            notes = "This method get access token for authentica API's",
            response = LoginResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = LoginResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return new ResponseEntity<>(service.signIn(signInRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/me")
    @ApiOperation(value = "Get account",
            notes = "This method get user information",
            response = AccountEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AccountEntity.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<AccountEntity> getMe(HttpServletRequest httpServletRequest) {
        var account = service.getIdentityFromToken(httpServletRequest);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }
}
