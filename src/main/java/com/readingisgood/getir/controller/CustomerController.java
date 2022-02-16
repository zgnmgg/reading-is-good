package com.readingisgood.getir.controller;

import com.readingisgood.getir.domain.model.OrderEntity;
import com.readingisgood.getir.domain.request.CustomerRequest;
import com.readingisgood.getir.domain.response.CustomerResponse;
import com.readingisgood.getir.service.CustomerService;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;
    private final OrderService orderService;

    @PostMapping
    @ApiOperation(value = "Create a customer",
            notes = "This method create a customer",
            response = CustomerResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = CustomerResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<CustomerResponse> createProduct(@RequestBody @Valid CustomerRequest request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @GetMapping(value = "/orders/{customerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "Get customer orders",
            notes = "This method get orders",
            response = OrderEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = OrderEntity.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    public ResponseEntity<List<OrderEntity>> getOrderByDateInterval(
            @PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(orderService.getAllByCustomer(customerId), HttpStatus.OK);
    }
}
