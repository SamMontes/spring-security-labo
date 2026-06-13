package com.server.app.mapper;

import com.server.app.dto.account.AccountRequest;
import com.server.app.dto.account.AccountResponse;
import com.server.app.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toEntity(AccountRequest dto) {
        Account entity = new Account();
        entity.setAlias(dto.getAlias());
        entity.setCurrency(dto.getCurrency());
        entity.setBaseBalance(dto.getBaseBalance());
        entity.setType(dto.getType());
        return entity;
    }

    public AccountResponse toDto(Account entity) {
        AccountResponse dto = new AccountResponse();
        dto.setId(entity.getId());
        dto.setAlias(entity.getAlias());
        return dto;
    }
}
