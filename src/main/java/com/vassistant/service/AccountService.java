package com.vassistant.service;


import com.vassistant.domain.PrimaryAccount;
import com.vassistant.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {
	
    PrimaryAccount createPrimaryAccount();
    
    SavingsAccount createSavingsAccount();
    
    void deposit(String accountType, double amount, Principal principal);
    
    void withdraw(String accountType, double amount, Principal principal);

    String savingsAccountBalance(String userId);

    String checkingAccountBalance(String userId);
}
