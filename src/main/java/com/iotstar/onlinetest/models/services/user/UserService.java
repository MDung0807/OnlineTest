package com.iotstar.onlinetest.models.services.user;

import com.iotstar.onlinetest.DTO.UserDTO;

public interface UserService {
    public void createUser(UserDTO userDTO);

    public void deleteUser(int userId);

    public UserDTO getUser(int userId);

    public UserDTO updateUser(UserDTO userDTO);
}
