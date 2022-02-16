package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.request.ProductRequest;
import com.readingisgood.getir.domain.request.UpdateStockRequest;
import com.readingisgood.getir.domain.response.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductEntity updateStock(Long id, UpdateStockRequest request);

    void save(ProductEntity entity);

    ProductEntity findById(Long id);
}
