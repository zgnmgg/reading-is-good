package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.request.CustomerRequest;
import com.readingisgood.getir.domain.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse save(CustomerRequest request);

    boolean existsById(Long id);
}
