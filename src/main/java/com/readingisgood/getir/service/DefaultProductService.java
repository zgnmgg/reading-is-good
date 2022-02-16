package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.mapper.ProductMapper;
import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.repo.ProductRepository;
import com.readingisgood.getir.domain.request.ProductRequest;
import com.readingisgood.getir.domain.request.UpdateStockRequest;
import com.readingisgood.getir.domain.response.ProductResponse;
import com.readingisgood.getir.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultProductService implements ProductService {

    private final ProductRepository repository;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest request) {
        final var product = ProductEntity.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .publishYear(request.getPublishYear())
                .stock(request.getStock())
                .price(BigDecimal.valueOf(request.getPrice()))
                .build();

        log.debug("New Product record to save: {}", product);
        this.repository.save(product);

        return ProductMapper.productToProductResponse(product);
    }

    @Override
    public void save(ProductEntity entity) {
        this.repository.save(entity);
    }

    @Override
    public ProductEntity updateStock(Long id, UpdateStockRequest request) {
        ProductEntity product = this.findById(id);
        product.setStock(request.getStock());
        log.debug(String.format("This product is updated: - updating the stock as: %s", request.getStock()));

        return this.repository.save(product);
    }

    @Override
    public ProductEntity findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
