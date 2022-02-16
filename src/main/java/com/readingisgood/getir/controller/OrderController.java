package com.readingisgood.getir.controller;

import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.request.OrderIntervalRequest;
import com.readingisgood.getir.domain.request.OrderRequest;
import com.readingisgood.getir.domain.response.OrderResponse;
import com.readingisgood.getir.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ApiOperation(value = "Create order",
            notes = "This method create order from customer",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = OrderResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get order by id",
            notes = "This method get specific order",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = OrderResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
    }


    @GetMapping("/all")
    @ApiOperation(value = "Get orders by interval",
            notes = "This method get orders start date and end date",
            response = OrderEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = OrderEntity.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<OrderEntity>> getOrderByDateInterval(@RequestBody @Valid OrderIntervalRequest request) throws ParseException {
        return new ResponseEntity<>(service.getOrdersByInterval(request), HttpStatus.OK);
    }
}
