package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.repo.ProductRepository;
import com.readingisgood.getir.domain.request.ProductRequest;
import com.readingisgood.getir.domain.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    @Spy
    private DefaultProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private static ProductRequest productRequest;

    @BeforeEach
    public void init() {
        productRequest = ProductRequest.builder()
                .name("Name")
                .author("Author")
                .stock(Long.valueOf("1"))
                .price(Long.valueOf("1"))
                .publishYear("123")
                .build();
    }

    @Test()
    public void createProductTest_whenProductRequestComesIn_thenReturnSuccessProductResponse() {
        // GIVEN
        ProductEntity product = ProductEntity.builder()
                .name("Name")
                .author("Author")
                .stock(Long.valueOf("1"))
                .price(BigDecimal.valueOf(1))
                .publishYear("123")
                .build();
        when(productRepository.save(any(ProductEntity.class)))
                .thenReturn(product);
        // WHEN
        ProductResponse productResponse = productService.create(productRequest);
        // THEN
        assertThat(productResponse.getName()).isEqualTo("Name");
    }
}