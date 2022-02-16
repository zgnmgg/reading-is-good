package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeExceptionImp {
    @Override
    public String getMessageKey()
    {
        return "FORBIDDEN";
    }

    @Override
    public HttpStatus getHttpStatus()
    {
        return HttpStatus.FORBIDDEN;
    }

    public ForbiddenException()
    {
        super();
    }
}
