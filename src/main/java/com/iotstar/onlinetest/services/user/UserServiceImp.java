package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UserNotFoundException;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Value("${ROLE_STUDENT}")
    private String roleStudent;

    private String uploadImage(MultipartFile fileImage, String fileName){
        String urlImage = null;
        if(fileImage.getSize()!= 0){
            urlImage = fileUtils.upload(fileImage, AppConstant.IMG_NAME_USER+fileName);
        }
        return urlImage;
    }
    @Override
    @Transactional
    public void createUser(UserRequest userRequest) {
        // skip map avatar
        mapper.typeMap(UserRequest.class, User.class).addMappings(mapper-> mapper.skip(User::setAvatar));
        // Get user
        user = mapper.map(userRequest, User.class);
        //Create user
        String urlImage = uploadImage(userRequest.getAvatar(), AppConstant.IMG_NAME_USER+userRequest.getUsername());
        user.setAvatar(urlImage);

        user.setDateCreate(LocalDateTime.now());
        user.setStatus(1);
        user = userDAO.save(user);

        //Get username and password
        accountDTO = mapper.map(userRequest, AccountDTO.class);
        accountDTO.setRoleName(roleStudent);
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

    @Override
    @Transactional
    public UserResponse updateAvatar(Long id, MultipartFile avatar){
        user = userDAO.findById(id).orElseThrow(()-> new ResourceNotFoundException(AppConstant.USER_NOTFOUND+id));
        String url = uploadImage(avatar, AppConstant.IMG_NAME_USER+id);
        user.setAvatar(url);
        user = userDAO.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserProfileRequest profileRequest){
        user = mapper.map(profileRequest, User.class);
        user = userDAO.save(user);
        return mapper.map(user, UserResponse.class);
    }
}
