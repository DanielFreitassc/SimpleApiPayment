package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.AccountRequestDto;
import com.danielfreitassc.backend.dtos.AccountResponseDto;
import com.danielfreitassc.backend.models.AccountEntity;

@Mapper(componentModel="spring")
public interface AccountMapper {

    @Mapping(target="user", source="userEntity")
    AccountResponseDto toDto(AccountEntity accountEntity);

    @Mapping(target="id",ignore=true)
    @Mapping(target="userEntity.id",source="userId")
    AccountEntity toEntity(AccountRequestDto accountRequestDto);
}
