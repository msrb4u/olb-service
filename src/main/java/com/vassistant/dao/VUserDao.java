package com.vassistant.dao;

import com.vassistant.domain.VUser;
import org.springframework.data.repository.CrudRepository;

public interface VUserDao extends CrudRepository<VUser, String> {
    VUser findByVuserId(String vuserId);
}
