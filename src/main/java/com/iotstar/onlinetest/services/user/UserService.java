package com.iotstar.onlinetest.services.user;

import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public void createUser(UserRequest userRequest);

    public void deleteUser(UserProfileRequest userProfileRequest);

    public UserResponse getUser(Long userId);

    public UserResponse updateAvatar(Long id, MultipartFile avatar);

    public UserResponse updateUser(UserProfileRequest userProfileRequest);
}
