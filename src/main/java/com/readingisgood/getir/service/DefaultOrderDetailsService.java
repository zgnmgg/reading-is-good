package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.OrderDetailEntity;
import com.readingisgood.getir.domain.repo.OrderDetailRepository;
import com.readingisgood.getir.domain.request.ProductDetailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultOrderDetailsService implements OrderDetailService {

    private final ProductService productService;
    private final OrderDetailRepository repository;

    @Transactional
    @Override
    public OrderDetailEntity save(ProductDetailRequest request) {

        final var orderDetail = OrderDetailEntity.builder()
                .quantity(request.getQuantity())
                .product(this.productService.findById(request.getId()))
                .build();

        log.debug("New Order Detail record to save: {}", orderDetail);
        this.repository.save(orderDetail);

        return orderDetail;
    }
}
