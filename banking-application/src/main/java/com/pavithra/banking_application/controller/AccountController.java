package com.pavithra.banking_application.controller;


import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pavithra.banking_application.model.Account;
import com.pavithra.banking_application.service.impl.AccountServiceImpl;

@Controller
public class AccountController {
	
	    @Autowired
	    private AccountServiceImpl accountServiceImpl;
		
		@GetMapping("/dashboard")
		public String dashboard(Model model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account account = accountServiceImpl.findAccountByUsername(username);
			model.addAttribute("account", account);
			return "dashboard";
		}
		
		@GetMapping("/register")
		public String showRegistrationForm() {
			return "register";
		}
		
		@PostMapping("/register")
		public String registerAccount(@RequestParam String username,@RequestParam String password,Model model) {
			try {
				accountServiceImpl.registerAccount(username,password);
				return "redirect:/login";
			}
			catch(RuntimeException e){
				model.addAttribute("error", e.getMessage());
				return "register";
			}
		}
		
		@GetMapping("/login")
		public String login() {
			return "login";
		}
		
		@PostMapping("/deposit")
		public String deposit(@RequestParam BigDecimal amount) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account account = accountServiceImpl.findAccountByUsername(username);
			accountServiceImpl.deposit(account, amount);
			return "redirect:/dashboard";
		}
		
		@PostMapping("/withdraw")
		public String withdraw(@RequestParam BigDecimal amount,Model model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account account = accountServiceImpl.findAccountByUsername(username);
			try {
				accountServiceImpl.withdraw(account,amount);
			}
			catch(RuntimeException e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("account", account);
				return "dashboard";
			}
			return "redirect:/dashboard";
		}
		
		@GetMapping("/transactions")
		public String transactionHistory(Model model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account account = accountServiceImpl.findAccountByUsername(username);
			model.addAttribute("transactions",accountServiceImpl.getTransactionHistory(account));
			return "transactions";
		}
		
		@PostMapping("/transfer")
		public String transferAmount(@RequestParam String toUsername,@RequestParam BigDecimal amount, Model model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account fromAccount = accountServiceImpl.findAccountByUsername(username);
			
			try {
				accountServiceImpl.transferAmount(fromAccount,toUsername,amount);
			}
			catch(RuntimeException e) {
				model.addAttribute("error",e.getMessage());
				model.addAttribute("account",fromAccount);
				return "dashboard";
			}
			
			return "redirect:/dashboard";
		}
		
		
		
		
		

}
