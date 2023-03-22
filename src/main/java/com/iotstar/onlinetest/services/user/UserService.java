package com.iotstar.onlinetest.services.user;

import com.iotstar.onlinetest.DTOs.RegisterForm.*;

public interface UserService {
    public UserDTO createUser(UserDTO userDTO);

    public void deleteUser(UserDTO userDTO);

    public UserDTO getUser(int userId);

    public void updateUser(UserDTO userDTO);
}
