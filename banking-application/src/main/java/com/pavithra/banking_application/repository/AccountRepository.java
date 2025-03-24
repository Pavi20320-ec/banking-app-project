package com.pavithra.banking_application.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pavithra.banking_application.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);

}

