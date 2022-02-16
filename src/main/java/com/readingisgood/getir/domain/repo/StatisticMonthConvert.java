package com.readingisgood.getir.domain.repo;

import java.math.BigDecimal;

public interface StatisticMonthConvert {
    String getMonth();

    Long getOrderCount();

    Long getProductCount();

    BigDecimal getAmount();
}