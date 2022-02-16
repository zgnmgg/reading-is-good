package com.readingisgood.getir.exception;

import com.readingisgood.getir.config.Translator;
import com.readingisgood.getir.exception.advice.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    private static final String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";

    @Autowired
    public RestResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.CONFLICT, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<Object> handleInvalidDataAccessApiUsage(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    protected ResponseEntity<Object> handlePropertyReference(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(CompletionException.class)
    protected ResponseEntity<Object> handleCompletion(CompletionException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    /*
     * ----------------------- Customs -----------------------
     */

    /*
     * ----------------------- Override Functions -----------------------
     */
    @Override
    @NonNull
    protected ResponseEntity<Object> handleBindException(@NonNull BindException exception,
                                                         @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {

        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale()))
                .collect(Collectors.toList());

        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, String.join("\n", errors), errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException exception,
                                                                  @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale()))
                .collect(Collectors.toList());

        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, String.join("\n", errors), errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(@NonNull MissingServletRequestParameterException exception,
                                                                          @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.BAD_REQUEST, Translator.toLocale("MissingRequestParameter.general", new Object[]{exception.getParameterType(), exception.getParameterName()})),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(@NonNull HttpMediaTypeNotAcceptableException exception,
                                                                      @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException exception,
                                                                  @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(
                        HttpStatus.NOT_ACCEPTABLE,
                        Translator.toLocale(UNEXPECTED_ERROR),
                        exception.getClass().getName() + ": " + exception.getLocalizedMessage()
                ),                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NonNull NoHandlerFoundException exception,
                                                                   @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        Translator.toLocale(UNEXPECTED_ERROR),
                        exception.getClass().getName() + ": " + exception.getLocalizedMessage()
                ),                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NonNull HttpRequestMethodNotSupportedException exception,
                                                                         @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(
                        HttpStatus.NOT_FOUND,
                        Translator.toLocale(UNEXPECTED_ERROR),
                        exception.getClass().getName() + ": " + exception.getLocalizedMessage()
                ),                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    //</editor-fold>

    //<editor-fold desc="Handling Project Exception">

    @ExceptionHandler({
            HttpRequestException.class,
            ForbiddenException.class,
            NotFoundException.class,
            ExistsException.class,
            StockNotAvailableException.class
    })
    protected ResponseEntity<Object> handleProjectExceptionCustom(RuntimeExceptionImp exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                new ErrorResponse(
                        exception.getHttpStatus(),
                        Translator.toLocale(exception.getMessageKey()),
                        exception.getMessage(),
                        exception.getErrorCode()
                ),
                new HttpHeaders(),
                exception.getHttpStatus(),
                request
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException exception, final WebRequest request) {
        logger.error("Unhandled error", exception);

        return handleExceptionInternal(
                exception,
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, Translator.toLocale("INTERNAL_SERVER_ERROR"), exception.getClass().getName() + ": " + exception.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
