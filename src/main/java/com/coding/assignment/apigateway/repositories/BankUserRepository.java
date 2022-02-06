package com.coding.assignment.apigateway.repositories;

import com.coding.assignment.apigateway.entities.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BankUserRepository extends JpaRepository<BankUser, UUID> {

    Optional<BankUser> findBankUserByAccessToken(String accessToken);
}
