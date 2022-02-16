package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.repo.StatisticMonthConvert;
import com.readingisgood.getir.domain.request.OrderIntervalRequest;
import com.readingisgood.getir.domain.request.OrderRequest;
import com.readingisgood.getir.domain.response.OrderResponse;

import java.text.ParseException;
import java.util.List;

public interface OrderService {

    OrderResponse save(OrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderEntity> getOrdersByInterval(OrderIntervalRequest request) throws ParseException;

    List<OrderEntity> getAllByCustomer(Long customerId);

    List<StatisticMonthConvert> getAllMontlyOrderByCustomer(Long customerId);
}
