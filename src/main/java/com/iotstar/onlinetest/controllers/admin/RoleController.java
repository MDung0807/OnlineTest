package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping({"/", ""})
    public List<RoleDTO> getRole(){
        return roleService.getAllRole();
    }

    @PostMapping({"", "/"})
    public RoleDTO getRoleByRoleName(@RequestBody RoleDTO roleDTO){
        return roleService.getRoleByRoleName(roleDTO.getRoleName());
    }

    @PostMapping("/add")
    public void addRole(@RequestBody RoleDTO roleDTO) throws Exception{
        roleService.createRole(roleDTO);
    }

    @PostMapping("/del")
    public void delRole(@RequestBody RoleDTO roleDTO){
        roleDTO = roleService.getRoleByRoleName(roleDTO.getRoleName());
        roleService.deleteRole(roleDTO);
    }

    @PostMapping("/update")
    public void updateRole(@RequestBody RoleDTO roleDTO){
        roleService.updateRole(roleDTO);
    }
}
