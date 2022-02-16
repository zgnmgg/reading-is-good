package com.readingisgood.getir.exception;

import com.readingisgood.getir.security.CustomException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;

public class ExceptionTest {

    @Test
    public void dateParseExceptionTest() {
        DateParseException dpe = new DateParseException();

        MatcherAssert.assertThat(dpe.getMessageKey(), is("EXCEPTION.parse"));
        MatcherAssert.assertThat(dpe.getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void httpRequestExceptionTest() {
        HttpRequestException hre = new HttpRequestException();

        MatcherAssert.assertThat(hre.getMessageKey(), is("service.unknown"));
        MatcherAssert.assertThat(hre.getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void existsExceptionTest() {
        ExistsException ee = new ExistsException();

        MatcherAssert.assertThat(ee.getMessageKey(), is("EXISTS"));
        MatcherAssert.assertThat(ee.getHttpStatus(), is(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    public void forbiddenException() {
        ForbiddenException fe = new ForbiddenException();

        MatcherAssert.assertThat(fe.getMessageKey(), is("FORBIDDEN"));
        MatcherAssert.assertThat(fe.getHttpStatus(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void notFoundExceptionTest() {
        NotFoundException nf = new NotFoundException();

        MatcherAssert.assertThat(nf.getMessageKey(), is("NOT_FOUND"));
        MatcherAssert.assertThat(nf.getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void userClientExistsExceptionTest() {
        StockNotAvailableException sae = new StockNotAvailableException();

        MatcherAssert.assertThat(sae.getMessageKey(), is("STOCK.NOT_AVAILABLE"));
        MatcherAssert.assertThat(sae.getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void customExceptionTest() {
        CustomException customException = new CustomException("message", HttpStatus.NOT_FOUND);

        MatcherAssert.assertThat(customException.getMessage(), is("message"));
        MatcherAssert.assertThat(customException.getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }
}
