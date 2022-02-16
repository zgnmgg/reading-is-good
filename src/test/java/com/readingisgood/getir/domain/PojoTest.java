package com.readingisgood.getir.domain;

import com.readingisgood.getir.domain.request.*;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class PojoTest {

    @Test
    public void createOrderIntervalRequestTest() {
        OrderIntervalRequest dto = new OrderIntervalRequest();
        dto.setStartDate("start");
        dto.setEndDate("end");

        MatcherAssert.assertThat(dto.getStartDate(), is("start"));
        MatcherAssert.assertThat(dto.getEndDate(), is("end"));
    }

    @Test
    public void createProductDetailRequestTest() {
        ProductDetailRequest dto = new ProductDetailRequest();
        dto.setId(Long.valueOf("1"));
        dto.setQuantity(4);

        MatcherAssert.assertThat(dto.getId(), is(Long.valueOf("1")));
        MatcherAssert.assertThat(dto.getQuantity(), is(4));
    }

    @Test
    public void createSignInRequestTest() {
        SignInRequest dto = new SignInRequest();

        dto.setEmail("demo@gmail.com");
        dto.setPassword("123");

        MatcherAssert.assertThat(dto.getEmail(), is("demo@gmail.com"));
        MatcherAssert.assertThat(dto.getPassword(), is("123"));
    }

    @Test
    public void updateStockRequestTest() {
        UpdateStockRequest dto = new UpdateStockRequest();

        dto.setStock(Long.valueOf("25"));

        MatcherAssert.assertThat(dto.getStock(), is(Long.valueOf("25")));
    }
}
