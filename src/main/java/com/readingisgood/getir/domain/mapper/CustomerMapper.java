package com.readingisgood.getir.domain.mapper;

import com.readingisgood.getir.domain.model.CustomerEntity;
import com.readingisgood.getir.domain.response.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse customerToCustomerResponse(CustomerEntity entity) {
        return CustomerResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhoneNumber())
                .address(entity.getAddress())
                .build();
    }
}
