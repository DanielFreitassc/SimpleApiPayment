package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

public record TransactionResponseDto(
    String message,
    BigDecimal amount
) {
    
}
