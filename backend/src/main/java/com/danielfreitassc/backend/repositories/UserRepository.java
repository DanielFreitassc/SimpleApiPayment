package com.danielfreitassc.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danielfreitassc.backend.models.UserEntity;

public interface  UserRepository extends JpaRepository<UserEntity,Long>{

    @Query("SELECT u FROM UserEntity u")
    Page<UserEntity> searchAllpaginatedUsers(Pageable pageable);
    
}
