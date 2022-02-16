package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.OrderDetailEntity;
import com.readingisgood.getir.domain.request.ProductDetailRequest;

public interface OrderDetailService {

    OrderDetailEntity save(ProductDetailRequest request);
}
