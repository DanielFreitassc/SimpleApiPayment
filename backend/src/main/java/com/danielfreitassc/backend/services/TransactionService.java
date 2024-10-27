package com.danielfreitassc.backend.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.TransactionRequestDTO;
import com.danielfreitassc.backend.dtos.TransactionResponseDto;
import com.danielfreitassc.backend.models.AccountEntity;
import com.danielfreitassc.backend.repositories.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
     private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    public TransactionResponseDto executeTransaction(TransactionRequestDTO transactionRequestDTO) {
        Optional<AccountEntity> from = accountRepository.findById(transactionRequestDTO.from_account_id());
        Optional<AccountEntity> to = accountRepository.findById(transactionRequestDTO.to_account_id());
        if(from.isEmpty() || to.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");

        AccountEntity fromEntity = from.get();
        BigDecimal newBalance = fromEntity.getBalance().subtract(transactionRequestDTO.amount());
        if(transactionRequestDTO.amount().compareTo(from.get().getBalance()) > 0) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sem saldo suficiente");
        
        fromEntity.setBalance(newBalance);
        AccountEntity toEntity = to.get();
        toEntity.setBalance(toEntity.getBalance().add(transactionRequestDTO.amount()));

        accountRepository.save(fromEntity);
        accountRepository.save(toEntity);
        
        TransactionResponseDto response = new TransactionResponseDto("Transferência bem sucessida", transactionRequestDTO.amount());

        messagingTemplate.convertAndSend("/topic/transactions",response);

        return response;
    }
    
}
