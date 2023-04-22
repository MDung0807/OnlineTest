package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private RoleResponse roleResponse;


    @GetMapping({"/", ""})
    public ResponseEntity<?> getRoles(){
        List<RoleResponse> roleResponses = roleService.getAllRole();
        return ResponseEntity.ok(new Response(false, roleResponses));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> getRoleByRoleName(@RequestBody RoleRequest roleRequest, BindingResult result){
        if (result.hasErrors()){

            return ResponseEntity.ok(
                    new Response(false, new MessageResponse(result.getObjectName()))
            );
        }
        roleResponse = roleService.getRoleByRoleName(roleRequest.getRoleName());

        return ResponseEntity.ok(new Response(false, roleResponse));
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
    public ResponseEntity<Response> updateRole(@RequestBody RoleRequest roleRequest){
        Response response = new Response(false, roleService.updateRole(roleRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
