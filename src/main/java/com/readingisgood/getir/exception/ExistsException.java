package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class ExistsException extends RuntimeExceptionImp {
    @Override
    public String getMessageKey()
    {
        return "EXISTS";
    }

    @Override
    public HttpStatus getHttpStatus()
    {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public ExistsException()
    {
        super();
    }
}
