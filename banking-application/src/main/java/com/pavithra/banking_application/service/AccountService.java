package com.pavithra.banking_application.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pavithra.banking_application.model.Account;
import com.pavithra.banking_application.model.Transaction;

public interface AccountService {

	Account findAccountByUsername(String username);
	
	Account registerAccount(String username,String password);
	
	void deposit(Account account, BigDecimal amount);
	
	void withdraw(Account account, BigDecimal amount);

	List<Transaction> getTransactionHistory(Account account);
	
	UserDetails loadUserByUsername(String username);
	
	Collection<? extends GrantedAuthority> authorities();
	
	void transferAmount(Account fromAccount, String toUsername, BigDecimal amount);
	
}
