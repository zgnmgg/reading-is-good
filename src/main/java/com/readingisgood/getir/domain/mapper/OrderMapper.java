package com.readingisgood.getir.domain.mapper;

import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.response.OrderResponse;

public class OrderMapper {
    public static OrderResponse orderToOrderResponse(OrderEntity entity) {
        return OrderResponse.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .date(entity.getCreatedDate())
                .status(entity.getStatus())
                .orderDetails(entity.getOrderDetailEntities())
                .amount(entity.getAmount().longValue())
                .build();
    }
}
