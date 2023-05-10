package com.iotstar.onlinetest.services.user;


import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.models.WishList;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.review.ReviewServiceImp;
import com.iotstar.onlinetest.services.wishList.WishListServiceImp;
import com.iotstar.onlinetest.statval.EUser;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AccountService accountService;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private WishListServiceImp wishListServiceImp;
    @Autowired
    private ReviewServiceImp reviewServiceImp;
    @Autowired
    private ModelMapper mapper;
    private User user;
    private AccountDTO accountDTO;
    @Value("${ROLE_STUDENT}")
    private String roleStudent;

//    private String uploadImage(MultipartFile fileImage, String fileName){
//        String urlImage = null;
//        if(fileImage !=  null){
//            urlImage = fileUtils.upload(fileImage, fileName);
//        }
//        return urlImage;
//    }

    public User getUserReturnUser(Long userId){
        return userDAO.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(EUser.USER_NOT_FOUND.getDescription(userId)));
    }

    public List<Question> getQuestionsInUser(Long userId) {
        return getUserReturnUser(userId).getQuestions();
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
            throw new ResourceExistException(EUser.EMAIL_EXISTS.getDescription());
        if(existsPhoneNumber(userRequest.getPhoneNumber()))
            throw new ResourceExistException(EUser.PHONE_NUMBER_EXISTS.getDescription());

        // skip map avatar
        mapper.typeMap(UserRequest.class, User.class).addMappings(mapper-> mapper.skip(User::setAvatar));
        // Get user
        user = mapper.map(userRequest, User.class);
        //Create user
        String urlImage = fileUtils.upload(userRequest.getAvatar(),
                EUser.IMG_NAME_USER.getDescription(userRequest.getUsername()));
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

        //Create WishList
        wishListServiceImp.createWishList(user);

        //Create Review
        reviewServiceImp.createReview(user);
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
        String url = fileUtils.upload(avatar, EUser.IMG_NAME_USER.getDescription(id));
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
                throw new ResourceExistException(EUser.EMAIL_EXISTS.getDescription());

        if (!user.getPhoneNumber().equals(userProfileRequest.getPhoneNumber()))
            if(existsPhoneNumber(userProfileRequest.getPhoneNumber()))
                throw new ResourceExistException(EUser.PHONE_NUMBER_EXISTS.getDescription());
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

    public Subject getSubjectInUser(Long userId){
        return getUserReturnUser(userId).getSubject();
    }
}
