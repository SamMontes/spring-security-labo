package com.server.app.controllers;

import com.server.app.dto.movement.MovementRequest;
import com.server.app.dto.movement.MovementResponse;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.services.MovementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/finanzas")
@AllArgsConstructor
public class MovementController {
    private final MovementService movementService;

    // GET /api/finanzas/movimientos
    @GetMapping("/movimientos")
    public ResponseEntity<Pagination<MovementResponse>> getMovements(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Pageable pageable) {

        Page<MovementResponse> page = movementService.getPaginatedMovements(start, end, pageable);

        PaginationMeta meta = new PaginationMeta(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
        Pagination<MovementResponse> paginatedResult = new Pagination<>(page.getContent(), meta);

        return ResponseEntity.ok(paginatedResult);
    }

    @PostMapping("/transferencias")
    public ResponseEntity<MovementResponse> makeTransfer(@Valid @RequestBody MovementRequest request) {
        return ResponseEntity.ok(movementService.makeTransfer(request));
    }
}
