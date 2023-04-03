package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.exceptions.UserNotFoundException;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
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
    private AccountService accountService;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private ModelMapper mapper;
    private User user;
    private AccountDTO accountDTO;
    @Override
    @Transactional
    public void createUser(UserRequest userRequest) {
        // skip map avatar
        mapper.typeMap(UserRequest.class, User.class).addMappings(mapper-> mapper.skip(User::setAvatar));
        // Get user
        user = mapper.map(userRequest, User.class);
        //Create user
        String urlImage = fileUtils.upload(userRequest.getAvatar(), userRequest.getUsername());
        user.setAvatar(urlImage);
        user.setDateCreate(LocalDateTime.now());
        user.setStatus(1);
        user = userDAO.save(user);

        //Get username and password
        accountDTO = mapper.map(userRequest, AccountDTO.class);
        accountDTO.setRoleName("user");
        //Create acc
        accountDTO.setUser(user);
        accountService.createAccount(accountDTO);
    }

    @Override
    public void deleteUser(UserProfileRequest userProfileRequest) {
        user = userDAO.findById(userProfileRequest.getUserId()).get();
        user.setStatus(0);
        user.getAccount().setStatus(0);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public UserResponse getUser(Long userId) {

        user = userDAO.getUserByUserId(userId).orElseThrow(()->
                new UserNotFoundException(AppConstant.USER_NOTFOUND+userId));
        return mapper.map(user, UserResponse.class);
    }

//    @Override
//    public void updateUser(UserDTO userDTO) {
//        user= User.builder()
//                .userId(userDTO.getUserId())
//                .firstName(userDTO.getFirstName())
//                .lastName(userDTO.getLastName())
//                .phoneNumber(userDTO.getPhoneNumber())
//                .avatar(userDTO.getAvatar())
//                .email(userDTO.getEmail())
//                .dateCreate(LocalDateTime.now())
//                .status(1)
//                .build();
//
//        userDAO.save(user);
//    }
}
