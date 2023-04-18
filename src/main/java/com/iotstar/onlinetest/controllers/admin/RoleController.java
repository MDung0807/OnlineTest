package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping({"/", ""})
    public ResponseEntity<?> getRoles(){
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> getRoleByRoleName(@RequestBody RoleRequest roleRequest, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.ok(new MessageResponse(result.getObjectName()));
        }
        return ResponseEntity.ok(roleService.getRoleByRoleName(roleRequest.getRoleName()));
    }

    @PostMapping("/add")
    public void addRole(@RequestBody RoleRequest roleRequest) {
        roleService.createRole(roleRequest);
    }

    @PostMapping("/del")
    public void delRole(@RequestBody RoleRequest roleRequest){
        roleService.deleteRole(roleRequest);
    }

    @PostMapping("/update")
    public void updateRole(@RequestBody RoleRequest roleRequest){
        roleService.updateRole(roleRequest);
    }
}
