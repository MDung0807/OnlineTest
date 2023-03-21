package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTO.RegisterForm.*;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;
    private User user;

    @Override
    @Transactional
    public void createUser(UserDTO userDTO, AccountDTO accountDTO) {
        Account account = Account.builder()
                .build();

        user= User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .avatar(userDTO.getAvatar())
                .email(userDTO.getEmail())
                .dateCreate(userDTO.getDateCreate())
                .status(userDTO.getStatus())
                .build();

        userDAO.save(user);
    }

    @Override
    public void deleteUser(int userId) {
    }

    @Override
    @Transactional
    public UserDTO getUser(int userId) {

        user = userDAO.getReferenceById(userId);

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
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }
}
