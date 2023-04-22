package com.iotstar.onlinetest.DTOs.responses;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type ="Bearer";
    private String email;
    private String phoneNumber;
    private String username;
    private List<String> roles;

    public JwtResponse(Long accountId, String jwt, String email, String phoneNumber, String username, List<String> roles) {
        this.email= email;
        this.id = accountId;
        this.token = jwt;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.roles = roles;
    }
}
