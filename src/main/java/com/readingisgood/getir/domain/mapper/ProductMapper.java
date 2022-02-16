package com.readingisgood.getir.domain.mapper;

import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse productToProductResponse(ProductEntity entity) {
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(entity.getAuthor())
                .publishYear(entity.getPublishYear())
                .price(entity.getPrice().longValue())
                .stock(entity.getStock())
                .build();
    }
}
