package com.readingisgood.getir.service;

import com.readingisgood.getir.domain.model.CustomerEntity;
import com.readingisgood.getir.domain.repo.CustomerRepository;
import com.readingisgood.getir.domain.request.CustomerRequest;
import com.readingisgood.getir.domain.response.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    @Spy
    private DefaultCustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private static CustomerRequest customerRequest;

    @BeforeEach
    public void init() {
        customerRequest = CustomerRequest.builder()
                .email("Name")
                .firstName("First")
                .lastName("LastName")
                .address("Adres")
                .phone("123")
                .build();
    }

    @Test()
    public void createCustomerTest_whenCustomerRequestComesIn_thenReturnSuccessCustomerResponse() {
        // GIVEN
        CustomerEntity customer =  CustomerEntity.builder()
                .email("Name")
                .firstName("First")
                .lastName("LastName")
                .address("Adres")
                .phoneNumber("123")
                .build();
        when(customerRepository.save(any(CustomerEntity.class)))
                .thenReturn(customer);
        // WHEN
        CustomerResponse customerResponse = customerService.save(customerRequest);
        // THEN
        assertThat(customerResponse.getEmail()).isEqualTo("Name");
    }


    @Test()
    public void existsCustomer() {

        boolean isActive = this.customerRepository.existsCustomerEntityByEmail("name");

        assertThat(isActive).isEqualTo(false);
    }
}