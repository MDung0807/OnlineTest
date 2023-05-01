package com.iotstar.onlinetest.controllers.admin.accountEndPoint;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/account")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
public interface IAccountController {
    @GetMapping({"/", ""})
    public List<AccountDTO> getAllAcc ();
    @GetMapping("/updateRole")
    public ResponseEntity<Response> updateRole(@Valid @RequestParam("userId") Long userId,
                                               @RequestParam("roleName") String roleName);
}
