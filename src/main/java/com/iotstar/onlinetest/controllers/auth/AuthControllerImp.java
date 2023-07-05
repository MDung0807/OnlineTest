package com.iotstar.onlinetest.controllers.auth;

import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.DTOs.requests.LoginRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.JwtResponse;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.security.jwt.JwtUtils;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.statval.EAccount;
import com.iotstar.onlinetest.statval.EUser;
import com.iotstar.onlinetest.utils.AppConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthControllerImp implements IAuthController{
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountService accountService;

    private UserResponse userResponse;


    @Override
    public ResponseEntity<?> createUser(UserRequest userParam1, MultipartFile image, UserRequest userParam2) {
        UserRequest userRequest;
        if(userParam2 ==null){
            userRequest = userParam1;
        }
        else {
            userRequest = userParam2;

        }
        if (image!= null)
            userRequest.setAvatar(image);
        userService.createUser(userRequest);
        MessageResponse messageResponse = new MessageResponse(EUser.USER_REGISTER_SUCCESS.getDescription());
        return ResponseEntity.ok(new Response(false, messageResponse));
    }

    @Override
    public ResponseEntity<?> login (LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok( new Response(false, new JwtResponse(
                accountDetails.getUserId(),
                jwt,
                accountDetails.getEmail(),
                accountDetails.getPhoneNumber(),
                accountDetails.getUsername(),
                roles))
        );
    }

    @Override
    public ResponseEntity<?> resetPass(AccountRequest accountRequest){
        accountService.update(accountRequest);
        MessageResponse messageResponse = new MessageResponse(EAccount.RESET_PASSWORD_SUCCESS.getDes());
        return ResponseEntity.ok(new Response(false, messageResponse));
    }


}
