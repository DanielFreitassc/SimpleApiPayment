package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

public record AccountResponseDto(
    Long id, 
    UserResponseDto user,
    BigDecimal balance
) {
    
}
