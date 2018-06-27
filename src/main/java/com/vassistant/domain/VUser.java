package com.vassistant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_user")
public class VUser {

    @Id
    @Column(name = "vuser_id", nullable = false, updatable = false)
    private String vuserId;
    @Column(name = "user_id", nullable = false, updatable = false)
    private String userId;

    public String getVuserId() {
        return vuserId;
    }

    public void setVuserId(String vuserId) {
        this.vuserId = vuserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
