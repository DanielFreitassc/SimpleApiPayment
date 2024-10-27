package com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
    
}
