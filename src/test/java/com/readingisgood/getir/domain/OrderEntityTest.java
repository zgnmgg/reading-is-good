package com.readingisgood.getir.domain;

import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.model.enums.OrderStatus;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class OrderEntityTest {

    @Test
    public void modelMethodTest() {
        OrderEntity model = new OrderEntity();

        model.setCustomerId(Long.valueOf("1"));
        model.setStatus(OrderStatus.COMPLETED);
        model.setOrderDetailEntities(null);
        model.setAmount(BigDecimal.valueOf(1));

        MatcherAssert.assertThat(model.getCustomerId(), is(Long.valueOf("1")));
        assertThat(model.getOrderDetailEntities()).isNull();
        MatcherAssert.assertThat(model.getStatus(), is(OrderStatus.COMPLETED));
        MatcherAssert.assertThat(model.getAmount(), is(BigDecimal.valueOf(1)));
    }
}
