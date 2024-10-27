package com.danielfreitassc.backend.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.AccountRequestDto;
import com.danielfreitassc.backend.dtos.AccountResponseDto;
import com.danielfreitassc.backend.dtos.TransactionRequestDTO;
import com.danielfreitassc.backend.dtos.TransactionResponseDto;
import com.danielfreitassc.backend.services.AccountService;
import com.danielfreitassc.backend.services.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping
    public AccountResponseDto create(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        return accountService.create(accountRequestDto);
    }

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PutMapping("/{id}")
    public AccountResponseDto update(@PathVariable Long id, @RequestBody @Valid AccountRequestDto accountRequestDto) {
        return accountService.update(id, accountRequestDto);
    }

    @DeleteMapping("/{id}")
    public AccountResponseDto delete(@PathVariable Long id) {
        return accountService.delete(id); 
    }

    @PostMapping("/transaction")
    public TransactionResponseDto executeTransaction(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        return  transactionService.executeTransaction(transactionRequestDTO);
    }
}
