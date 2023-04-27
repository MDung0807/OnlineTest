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
import com.iotstar.onlinetest.utils.AppConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountService accountService;

    private UserResponse userResponse;

    @RequestMapping(value = "auth/register")
    public ResponseEntity<?> createUser(@Valid @ModelAttribute  UserRequest userParam1,
                                        @ModelAttribute MultipartFile avatar,
                                        @Valid @RequestPart(value = "user", required = false)UserRequest userParam2) {

        UserRequest userRequest;
        if(userParam2 ==null){
            userRequest = userParam1;
        }
        else {
            userRequest = userParam2;

        }
        userRequest.setAvatar(avatar);
        userService.createUser(userRequest);
        MessageResponse messageResponse = new MessageResponse(AppConstant.USER_REGISTER_SUCCESS);
        return ResponseEntity.ok(new Response(false, messageResponse));
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest)throws Exception{
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
                accountDetails.getAccountId(),
                jwt,
                accountDetails.getEmail(),
                accountDetails.getPhoneNumber(),
                accountDetails.getUsername(),
                roles))
        );
    }

    @PostMapping("/auth/reset")
    public ResponseEntity<?> resetPass(@RequestBody @Valid AccountRequest accountRequest, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return ResponseEntity.ok(result.getFieldError().getDefaultMessage());
        }
        accountService.update(accountRequest);
        MessageResponse messageResponse = new MessageResponse( AppConstant.RESET_PASSWORD_SUCCESS);
        return ResponseEntity.ok(new Response(false, messageResponse));
    }


}
