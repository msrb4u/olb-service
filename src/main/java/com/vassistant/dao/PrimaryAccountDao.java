package com.vassistant.dao;


import com.vassistant.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount,Long> {

    PrimaryAccount findByAccountNumber(int accountNumber);
    PrimaryAccount findById(long userId);
}
