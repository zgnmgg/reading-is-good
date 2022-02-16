package com.readingisgood.getir.domain.repo;

import com.readingisgood.getir.domain.model.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseJpaRepository<AccountEntity, Long>
{
    Optional<AccountEntity> findByEmail(String email);

    boolean existsAccountEntityByName(String name);

    boolean existsAccountEntityByEmail(String email);
}