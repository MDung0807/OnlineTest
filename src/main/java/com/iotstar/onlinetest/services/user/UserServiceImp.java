package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UserNotFoundException;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;


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
        if(fileImage !=  null){
            urlImage = fileUtils.upload(fileImage, AppConstant.IMG_NAME_USER+fileName);
        }
        return urlImage;
    }

    public User getUserReturnUser(Long userId){
        return userDAO.findById(userId).orElseThrow(()->new ResourceNotFoundException(AppConstant.USER_NOTFOUND+userId));
    }

    @Override
    public Boolean existsEmail(String emailInput){
        return userDAO.existsByEmail(emailInput);
    }
    @Override
    public Boolean existsPhoneNumber(String phoneNumber){
        return userDAO.existsByPhoneNumber(phoneNumber);
    }
    @Override
    @Transactional
    public void createUser(UserRequest userRequest) {
        if (existsEmail(userRequest.getEmail()))
            throw new ResourceExistException(AppConstant.EMAIL_EXISTS);
        if(existsPhoneNumber(userRequest.getPhoneNumber()))
            throw new ResourceExistException(AppConstant.PHONE_NUMBER_EXISTS);

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
    public void deleteUser(Long userId) {
        user = getUserReturnUser(userId);
        user.setStatus(0);
        user.getAccount().setStatus(0);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public UserResponse getUser(Long userId) {

        user = getUserReturnUser(userId);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateAvatar(Long id, MultipartFile avatar){
        user = getUserReturnUser(id);
        String url = uploadImage(avatar, AppConstant.IMG_NAME_USER+id);
        user.setAvatar(url);
        user = userDAO.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserProfileRequest userProfileRequest){
        user = getUserReturnUser(userProfileRequest.getUserId());

        //email input != email old => check email input exists??
        if(!user.getEmail().equals(userProfileRequest.getEmail()))
            if (existsEmail(userProfileRequest.getEmail()))
                throw new ResourceExistException(AppConstant.EMAIL_EXISTS);

        if (!user.getPhoneNumber().equals(userProfileRequest.getPhoneNumber()))
            if(existsPhoneNumber(userProfileRequest.getPhoneNumber()))
                throw new ResourceExistException(AppConstant.PHONE_NUMBER_EXISTS);
        // change data
        user.setGender(userProfileRequest.getGender());
        user.setFirstName(userProfileRequest.getFirstName());
        user.setLastName(userProfileRequest.getLastName());
        user.setEmail(userProfileRequest.getEmail());
        user.setPhoneNumber(userProfileRequest.getPhoneNumber());

        user = userDAO.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public Boolean existsSubject(Long userId) {
        user = getUserReturnUser(userId);
        return user.getSubject() != null;
    }

    @Override
    public Boolean existsSubjectById(Long userId, Long subjectId){
        user = getUserReturnUser(userId);
        if (user.getSubject()!= null){
            return Objects.equals(user.getSubject().getSubjectId(), subjectId);
        }
        return false;
    }

    //Insert into user value subject
    @Override
    public void addSubjectInUser(Long userId, Subject subject){
        user = getUserReturnUser(userId);
        user.setSubject(subject);
        userDAO.save(user);
    }
}
