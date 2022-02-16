package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.mapper.CustomerMapper;
import com.readingisgood.getir.domain.model.CustomerEntity;
import com.readingisgood.getir.domain.repo.CustomerRepository;
import com.readingisgood.getir.domain.request.CustomerRequest;
import com.readingisgood.getir.domain.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository repository;

    @Override
    @Transactional
    public CustomerResponse save(CustomerRequest request) {
        final var customer = CustomerEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .phoneNumber(request.getPhone())
                .build();

        log.debug("New Customer record to save: {}", customer);
        this.repository.save(customer);

        return CustomerMapper.customerToCustomerResponse(customer);
    }

    @Override
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }
}
