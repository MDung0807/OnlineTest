package com.iotstar.onlinetest.DTO;

import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterForm {
    private UserDTO userDTO;
    private AccountDTO accountDTO;

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
    }

    @Data
    @Builder
    public static class AccountDTO{
        private int accountId;
        private String password;
        private String username;
        private Role role;
        private User user;
    }
}


