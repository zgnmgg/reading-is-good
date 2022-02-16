package com.readingisgood.getir.controller;

import com.readingisgood.getir.domain.model.ProductEntity;
import com.readingisgood.getir.domain.request.ProductRequest;
import com.readingisgood.getir.domain.request.UpdateStockRequest;
import com.readingisgood.getir.domain.response.ProductResponse;
import com.readingisgood.getir.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ApiOperation(value = "Create product",
            notes = "This method create a product",
            response = ProductResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ProductResponse.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update product stock",
            notes = "This method update unique product stock",
            response = ProductEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ProductEntity.class, message = "Success"),
            @ApiResponse(code = 401, message = "Bu isleme yetkiniz bulunamamaktadir.") })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ProductEntity> updateStock(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateStockRequest request
    ) {
        return new ResponseEntity<>(service.updateStock(id, request), HttpStatus.OK);
    }
}
