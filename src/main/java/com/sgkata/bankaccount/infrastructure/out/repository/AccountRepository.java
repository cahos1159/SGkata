package com.sgkata.bankaccount.infrastructure.out.repository;

import com.sgkata.bankaccount.infrastructure.out.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountJpaEntity, String> {
    @Override
    Optional<AccountJpaEntity> findById(String id);

}
