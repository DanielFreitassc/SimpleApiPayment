package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestDTO(
    @NotNull(message="Usuário não pode ser nulo")
    Long from_account_id,
    @NotNull(message="Usuário não pode ser nulo")
    Long to_account_id,
    @NotNull(message="Valor da transação não pode ser nula") @DecimalMin(value = "0.01", message = "O Valor da transação deve ser acima de 1 centavo")
    BigDecimal amount
) {
    
}
