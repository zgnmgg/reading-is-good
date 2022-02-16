package com.readingisgood.getir.domain.response;

import com.readingisgood.getir.domain.model.OrderDetailEntity;
import com.readingisgood.getir.domain.model.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private Long customerId;
    private Date date;
    private OrderStatus status;
    private List<OrderDetailEntity> orderDetails;
    private Long amount;
}
