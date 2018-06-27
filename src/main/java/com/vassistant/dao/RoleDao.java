package com.vassistant.dao;


import com.vassistant.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {
    
    Role findByName(String name);
}
