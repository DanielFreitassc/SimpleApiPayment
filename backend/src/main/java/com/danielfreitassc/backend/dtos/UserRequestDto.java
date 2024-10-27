package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
    @NotBlank(message="O nome não pode estar em branco")
    String name
) {
    
}
