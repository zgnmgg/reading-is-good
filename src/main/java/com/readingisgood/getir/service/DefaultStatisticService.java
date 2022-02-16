package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.repo.StatisticMonthConvert;
import com.readingisgood.getir.domain.response.MonthReportResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultStatisticService implements StatisticService {

    private final OrderService orderService;

    @Override
    public List<MonthReportResponse> getAllMontlyOrderByCustomer(Long customerId) {
        List<StatisticMonthConvert> list = orderService.getAllMontlyOrderByCustomer(customerId);

        return list.stream()
                .map(MonthReportResponse::build).collect(Collectors.toList());
    }
}
