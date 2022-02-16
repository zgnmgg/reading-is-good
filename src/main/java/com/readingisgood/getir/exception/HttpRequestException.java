package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class HttpRequestException extends RuntimeExceptionImp {

    @Override
    public String getMessageKey() {
        return "service.unknown";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public HttpRequestException() {
        super();
    }
}
