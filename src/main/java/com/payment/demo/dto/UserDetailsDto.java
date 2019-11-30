package com.payment.demo.dto;

import com.payment.demo.CardAuthorizationInfo;

public class UserDetailsDto {
    private String username;
    private CardAuthorizationInfo status;

    public UserDetailsDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CardAuthorizationInfo getStatus() {
        return status;
    }

    public void setStatus(CardAuthorizationInfo status) {
        this.status = status;
    }
}
