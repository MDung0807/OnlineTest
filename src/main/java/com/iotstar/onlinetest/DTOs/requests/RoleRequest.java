package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {
    private Long roleId;
    @Size(max = 30, message = "Role name have length less then equal 30")
    private String roleName;
}


