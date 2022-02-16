package com.readingisgood.getir.controller;

import com.readingisgood.getir.domain.response.MonthReportResponse;
import com.readingisgood.getir.service.StatisticService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("monthly/{customerId}")
    @ApiOperation(value = "Monthly order api",
            notes = "This method get monthly report",
            response = MonthReportResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = MonthReportResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    public ResponseEntity<List<MonthReportResponse>> getMonthlyStatistics(@PathVariable Long customerId) {
        return ResponseEntity.ok(statisticService.getAllMontlyOrderByCustomer(customerId));
    }
}
