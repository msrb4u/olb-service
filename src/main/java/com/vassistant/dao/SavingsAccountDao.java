package com.vassistant.dao;


import com.vassistant.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber(int accountNumber);
    SavingsAccount findById(long userId);
}
