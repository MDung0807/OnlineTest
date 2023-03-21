package com.iotstar.onlinetest.services.user;

import com.iotstar.onlinetest.DTO.RegisterForm.*;

public interface UserService {
    public void createUser(UserDTO userDTO, AccountDTO accountDTO);

    public void deleteUser(int userId);

    public UserDTO getUser(int userId);

    public UserDTO updateUser(UserDTO userDTO);
}
