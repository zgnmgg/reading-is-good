package com.readingisgood.getir.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.getir.config.Translator;
import com.readingisgood.getir.exception.advice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

@Component
@SuppressWarnings("Duplicates")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED,
                Translator.toLocaleSpecified("UNAUTHORIZED.general", httpServletRequest.getLocale()),
                Collections.singletonList(e.getLocalizedMessage())
        );

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(errorResponse.getStatus().value());

        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorResponse);
        out.flush();
    }
}