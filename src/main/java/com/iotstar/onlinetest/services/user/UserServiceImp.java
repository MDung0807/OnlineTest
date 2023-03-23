package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTOs.RegisterForm.*;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private ModelMapper mapper;
    private User user;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {

        user = mapper.map(userDTO, User.class);
        user.setDateCreate(LocalDateTime.now());
        user.setStatus(1);
        user = userDAO.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        userDTO.setStatus(0);
        updateUser(userDTO);
    }

    @Override
    @Transactional
    public UserDTO getUser(int userId) {

        user = userDAO.getUserByUserId(userId);

        return UserDTO.builder()
                .userId(user.getUserId())
                .avatar(user.getAvatar())
                .dateCreate(user.getDateCreate())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        user= User.builder()
                .userId(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .avatar(userDTO.getAvatar())
                .email(userDTO.getEmail())
                .dateCreate(LocalDateTime.now())
                .status(1)
                .build();

        userDAO.save(user);
    }
}
