package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.mapper.OrderMapper;
import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.model.enums.OrderStatus;
import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.repo.OrderRepository;
import com.readingisgood.getir.domain.repo.StatisticMonthConvert;
import com.readingisgood.getir.domain.request.OrderIntervalRequest;
import com.readingisgood.getir.domain.request.OrderRequest;
import com.readingisgood.getir.domain.response.OrderResponse;
import com.readingisgood.getir.exception.NotFoundException;
import com.readingisgood.getir.exception.StockNotAvailableException;
import com.readingisgood.getir.helper.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultOrderService implements OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderDetailService orderDetailService;

    @Transactional
    @Override
    public OrderResponse save(OrderRequest request) {

        this.orderValidation(request);

        request.getProductOrderDetails().forEach(orderDetail -> {
            ProductEntity product = productService.findById(orderDetail.getId());
            product.setStock(product.getStock() - orderDetail.getQuantity());
            productService.save(product);
        });

        final var order = OrderEntity.builder()
                .customerId(request.getCustomerId())
                .status(OrderStatus.PREPARING)
                .amount(request.getOrderAmount())
                .orderDetailEntities(request.getProductOrderDetails().stream()
                    .map(this.orderDetailService::save).collect(Collectors.toList()))
                .build();

        log.debug("New Order record to save: {}", order);
        this.repository.save(order);
        return OrderMapper.orderToOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return OrderMapper.orderToOrderResponse(this.repository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<OrderEntity> getOrdersByInterval(OrderIntervalRequest request) throws ParseException {
        return this.repository.findOrderEntityByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
                Utils.convertStringToDate(request.getStartDate()),
                Utils.convertStringToDate(request.getEndDate())
            );
    }

    @Override
    public List<OrderEntity> getAllByCustomer(Long customerId) {
        return this.repository.findAllByCustomerId(customerId);
    }

    @Override
    public List<StatisticMonthConvert> getAllMontlyOrderByCustomer(Long customerId) {
        return this.repository.getAllMontlyOrderByCustomer(customerId);
    }

    public void orderValidation(OrderRequest request){

        if(! this.customerService.existsById(request.getCustomerId())){
            throw new NotFoundException();
        }

        if(! request.getProductOrderDetails().stream().allMatch(orderDetail -> {
            ProductEntity product = productService.findById(orderDetail.getId());
            return product.getStock().compareTo(Long.valueOf(orderDetail.getQuantity())) >= 0;
        })) {
            throw new StockNotAvailableException();
        }
    }
}
