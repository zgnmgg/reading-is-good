package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public class StockNotAvailableException extends RuntimeExceptionImp
{
    @Override
    public String getMessageKey()
    {
        return "STOCK.NOT_AVAILABLE";
    }

    @Override
    public HttpStatus getHttpStatus()
    {
        return HttpStatus.NOT_FOUND;
    }

    public StockNotAvailableException()
    {
        super();
    }
}
