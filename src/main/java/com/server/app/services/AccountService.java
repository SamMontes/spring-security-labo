package com.server.app.services;

import com.server.app.dto.account.AccountRequest;
import com.server.app.dto.account.AccountResponse;
import com.server.app.entities.Account;
import com.server.app.entities.User;
import com.server.app.mapper.AccountMapper;
import com.server.app.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Transactional(readOnly = true)
    public List<AccountResponse> getAccount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return repository.findByUserId(user.getId()).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = mapper.toEntity(request);
        account.setUserId(user.getId());
        if (account.getBaseBalance() == null) account.setBaseBalance(0.0);

        return mapper.toDto(repository.save(account));
    }
}
