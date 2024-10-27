package com.danielfreitassc.backend.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.AccountRequestDto;
import com.danielfreitassc.backend.dtos.AccountResponseDto;
import com.danielfreitassc.backend.mappers.AccountMapper;
import com.danielfreitassc.backend.models.AccountEntity;
import com.danielfreitassc.backend.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponseDto create(AccountRequestDto acaAccountRequestDto) {
        return  accountMapper.toDto(accountRepository.save(accountMapper.toEntity(acaAccountRequestDto)));
    }

    public AccountResponseDto getAccount(Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Conta não encontrada");
        return accountMapper.toDto(account.get());
    }

    public AccountResponseDto update(Long id, AccountRequestDto accountRequestDto) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Conta não encontrada");
        AccountEntity accountEntity = accountMapper.toEntity(accountRequestDto);
        accountEntity.setId(id);
        return accountMapper.toDto(accountRepository.save(accountEntity));
    }

    public AccountResponseDto delete(Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Conta não encontrada");
        accountRepository.delete(account.get());
        return accountMapper.toDto(account.get());
    }

}
