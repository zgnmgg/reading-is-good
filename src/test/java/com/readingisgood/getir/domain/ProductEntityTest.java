package com.readingisgood.getir.domain;

import com.readingisgood.getir.domain.model.ProductEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class ProductEntityTest {

    @Test
    public void modelMethodTest() {
        ProductEntity model = new ProductEntity();

        model.setName("name");
        model.setAuthor("author");
        model.setStock(Long.valueOf("1"));
        model.setPrice(BigDecimal.valueOf(1));
        model.setPublishYear("2012");

        MatcherAssert.assertThat(model.getName(), is("name"));
        MatcherAssert.assertThat(model.getAuthor(), is("author"));
        MatcherAssert.assertThat(model.getStock(), is(Long.valueOf("1")));
        MatcherAssert.assertThat(model.getPrice(), is(BigDecimal.valueOf(1)));
        MatcherAssert.assertThat(model.getPublishYear(), is("2012"));

    }
}
