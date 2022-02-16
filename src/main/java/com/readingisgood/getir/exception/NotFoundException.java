package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeExceptionImp {
    @Override
    public String getMessageKey()
    {
        return "NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus()
    {
        return HttpStatus.NOT_FOUND;
    }

    public NotFoundException()
    {
        super();
    }
}