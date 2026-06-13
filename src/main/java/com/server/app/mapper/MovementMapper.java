package com.server.app.mapper;

import com.server.app.dto.account.AccountResponse;
import com.server.app.dto.category.CategoryResponse;
import com.server.app.dto.movement.MovementRequest;
import com.server.app.dto.movement.MovementResponse;
import com.server.app.entities.Account;
import com.server.app.entities.Category;
import com.server.app.entities.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public Movement toEntity(MovementRequest req) {
        Movement mov = new Movement();
        mov.setAmount(req.getAmount());
        mov.setDescription(req.getDescription());

        Account acc = new Account();
        acc.setId(req.getAccountId());
        mov.setAccount(acc);

        Category cat = new Category();
        cat.setId(req.getAccountId());
        mov.setCategory(cat);

        return mov;
    }

    public MovementResponse toResponse(Movement mov) {
        AccountResponse acc = new AccountResponse(
                mov.getAccount().getId(),
                mov.getAccount().getAlias()
        );

        CategoryResponse cat = new CategoryResponse(
                mov.getCategory().getId(),
                mov.getCategory().getName(),
                mov.getCategory().getType()
        );

        return new MovementResponse(
                mov.getId(),
                mov.getAmount(),
                mov.getDate(),
                mov.getDescription(),
                acc,
                cat
        );
    }
}
