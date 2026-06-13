package com.server.app.services;

import com.server.app.dto.movement.MovementRequest;
import com.server.app.dto.movement.MovementResponse;
import com.server.app.entities.Account;
import com.server.app.entities.Movement;
import com.server.app.mapper.MovementMapper;
import com.server.app.repositories.AccountRepository;
import com.server.app.repositories.MovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper mapper;

    @Transactional(readOnly = true)
    public Page<MovementResponse> getPaginatedMovements(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        if (start != null && end != null) {
            return movementRepository.findByDateBetween(start, end, pageable).map(mapper::toResponse);
        }
        return movementRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional
    public MovementResponse makeTransfer(MovementRequest request) {
        Account cuentaOrigen = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        if (cuentaOrigen.getBaseBalance() < request.getAmount()) {
            throw new RuntimeException("Fondos insuficientes para realizar la transferencia");
        }

        cuentaOrigen.setBaseBalance(cuentaOrigen.getBaseBalance() - request.getAmount());
        accountRepository.save(cuentaOrigen);

        Movement movimiento = mapper.toEntity(request);
        movimiento.setDate(LocalDateTime.now());

        return mapper.toResponse(movementRepository.save(movimiento));
    }
}
