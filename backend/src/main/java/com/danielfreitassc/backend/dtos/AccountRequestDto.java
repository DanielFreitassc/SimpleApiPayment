package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record AccountRequestDto(
    @NotNull(message="Usuário não pode ser nulo")
    Long userId,
    BigDecimal balance
) {
    
}
