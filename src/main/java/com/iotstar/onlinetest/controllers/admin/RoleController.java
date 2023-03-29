package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping({"/", ""})
    @PreAuthorize("hasRole('admin')")
    public List<RoleResponse> getRole(){
        return roleService.getAllRole();
    }

    @PostMapping({"", "/"})
    public RoleResponse getRoleByRoleName(@RequestBody RoleRequest roleRequest){
        return roleService.getRoleByRoleName(roleRequest.getRoleName());
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
