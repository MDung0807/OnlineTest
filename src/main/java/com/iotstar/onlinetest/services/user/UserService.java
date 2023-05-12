package com.iotstar.onlinetest.services.user;

import com.iotstar.onlinetest.DTOs.requests.ResetPassword;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void createUser(UserRequest userRequest);

    void deleteUser(Long userId);

    UserResponse getUser(Long userId);

    UserResponse updateAvatar(Long id, MultipartFile avatar);

    UserResponse updateUser(UserProfileRequest userProfileRequest);
    Boolean existsEmail (String email);
    Boolean existsPhoneNumber(String PhoneNumber);
    Boolean existsSubject(Long userId);
    Boolean existsSubjectById(Long userId, Long subjectId);
    void addSubjectInUser(Long userid, Subject subject);
    void updatePassword(ResetPassword resetPassword, Long userId);
}
