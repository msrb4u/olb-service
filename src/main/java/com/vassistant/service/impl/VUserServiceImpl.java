package com.vassistant.service.impl;

import com.vassistant.dao.VUserDao;
import com.vassistant.domain.VUser;
import com.vassistant.service.VUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VUserServiceImpl implements VUserService{

    @Autowired
    private VUserDao vuserDao;

    public VUser findByVuserId(String vuserId) {
        return vuserDao.findByVuserId(vuserId);
    }
}
