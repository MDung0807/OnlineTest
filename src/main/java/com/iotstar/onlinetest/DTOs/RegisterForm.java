package com.iotstar.onlinetest.DTOs;

import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterForm {
    private UserDTO user;
    private AccountDTO account;
    private String roleName;
    @Data
    @Builder
    public static class UserDTO{
        private int userId;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String avatar;
        private LocalDateTime dateCreate;
        private int status;
        private Account account;
    }

    @Data
    @Builder
    public static class AccountDTO{
        private int accountId;
        private String password;
        private String username;
        private int status;
        private Role role;
        private User user;
    }
}


