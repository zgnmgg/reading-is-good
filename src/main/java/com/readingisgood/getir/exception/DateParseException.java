package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class DateParseException extends RuntimeExceptionImp {

    @Override
    public String getMessageKey() {
        return "EXCEPTION.parse";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public DateParseException() {
        super();
    }

}

