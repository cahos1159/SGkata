package com.sgkata.bankaccount.infrastructure.mapper;

import com.sgkata.bankaccount.domain.model.Account;
import com.sgkata.bankaccount.infrastructure.out.entity.AccountJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountJpaEntity mapToAccountJpaEntity(Account account) {
        AccountJpaEntity result = new AccountJpaEntity();
        if(account == null){
            return result;
        }
        if (account.getAccountId() != null) {
            result.setAccountId(account.getAccountId());
        }
        result.setBalance(account.getBalance());
        result.setOwner(account.getOwner());
        result.setBalance(account.getBalance());
        return result;
    }

    public Account mapToAccount(AccountJpaEntity accountJpaEntity) {
        Account result = new Account();
        if(accountJpaEntity == null){
            return result;
        }
        if (accountJpaEntity.getAccountId() != null) {
            result.setAccountId(accountJpaEntity.getAccountId());
        }
        result.setBalance(accountJpaEntity.getBalance());
        result.setOwner(accountJpaEntity.getOwner());
        result.setBalance(accountJpaEntity.getBalance());
        return result;
    }
}
