package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTO.RoleDTO;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping({"/", ""})
    public RoleDTO getRole(){
        return roleService.getAllRole();
    }

    @PostMapping({"", "/"})
    public RoleDTO getRoleByRoleName(@RequestBody String roleName){
        return roleService.getRoleByRoleName(roleName);
    }

    @PostMapping("/add")
    public void addRole(@RequestBody RoleDTO roleDTO) throws Exception{
        roleService.createRole(roleDTO);
    }

    @DeleteMapping("")
    public void delRole(@RequestParam int id){
        roleService.deleteRole(id);
    }
}
