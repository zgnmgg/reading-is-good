package com.readingisgood.getir.domain.repo;

import com.readingisgood.getir.domain.model.CustomerEntity;

public interface CustomerRepository extends BaseJpaRepository<CustomerEntity, Long> {

    boolean existsCustomerEntityByEmail(String email);
}
