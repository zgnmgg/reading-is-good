package com.readingisgood.getir.domain.response;

import com.readingisgood.getir.domain.repo.StatisticMonthConvert;
import com.readingisgood.getir.helper.Utils;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MonthReportResponse {
    private String month;
    private Long orderCount;
    private Long productCount;
    private BigDecimal amount;

    public static MonthReportResponse build(StatisticMonthConvert convert) {
        return MonthReportResponse.builder()
                .month(Utils.getMonth(convert.getMonth()))
                .productCount(convert.getProductCount())
                .orderCount(convert.getOrderCount())
                .amount(convert.getAmount())
                .build();
    }
}
