package com.readingisgood.getir.exception;

import org.springframework.http.HttpStatus;

public abstract class RuntimeExceptionImp extends RuntimeException {

    public abstract String getMessageKey();
    public abstract HttpStatus getHttpStatus();

    public int getErrorCode() {
        return 100;
    }

    public RuntimeExceptionImp() {
        super();
    }

    public RuntimeExceptionImp(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RuntimeExceptionImp(final String message) {
        super(message);
    }

    public RuntimeExceptionImp(final Throwable cause) {
        super(cause);
    }
}
