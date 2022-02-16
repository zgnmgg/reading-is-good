package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.response.MonthReportResponse;

import java.util.List;

public interface StatisticService {
    List<MonthReportResponse> getAllMontlyOrderByCustomer(Long customerId);
}
