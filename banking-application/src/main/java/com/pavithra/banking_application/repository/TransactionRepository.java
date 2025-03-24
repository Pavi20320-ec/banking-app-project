package com.pavithra.banking_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavithra.banking_application.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
  
	List<Transaction> findByAccountId(Long accountId);
}
