package com.vassistant.dao;


import com.vassistant.domain.User;
import com.vassistant.domain.VUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
    
	User findByUsername(String username);
	
    User findByEmail(String email);
    
    List<User> findAll();

    User findByUserId(long userId);
}
