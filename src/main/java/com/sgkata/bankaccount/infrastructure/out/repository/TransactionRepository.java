package com.sgkata.bankaccount.infrastructure.out.repository;

import com.sgkata.bankaccount.infrastructure.out.entity.TransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionJpaEntity, String> {
    @Query("""
			select a from TransactionJpaEntity a
			where a.targetAccount = :accountId
			or a.sourceAccount = :accountId
			""")
    List<TransactionJpaEntity> findAllTransaction(@Param("accountId") String accountId);
}
