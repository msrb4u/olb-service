package com.vassistant.service;

import com.vassistant.domain.VUser;

public interface VUserService {
    VUser findByVuserId(String vuserId);
}
