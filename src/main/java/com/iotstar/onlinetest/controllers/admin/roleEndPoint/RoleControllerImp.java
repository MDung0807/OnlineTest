package com.iotstar.onlinetest.controllers.admin.roleEndPoint;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.services.role.RolePaging;
import com.iotstar.onlinetest.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleControllerImp implements IRoleController{
    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePaging paging;


    @Override
    public ResponseEntity<?> getRoles(int index, int size){
        paging.setPageSize(size);
        paging.setPageIndex(index);

        List<RoleResponse> roleResponses = roleService.getAllRole();
        return ResponseEntity.ok(new Response(false, roleResponses));
    }

    @Override
    public ResponseEntity<?> getRoleByRoleName( RoleRequest roleRequest){
        RoleResponse roleResponse = roleService.getRoleByRoleName(roleRequest.getRoleName());

        return ResponseEntity.ok(new Response(false, roleResponse));
    }

    @Override
    public void addRole(RoleRequest roleRequest) {
        roleService.createRole(roleRequest);
    }

    @Override
    public void delRole(RoleRequest roleRequest){
        roleService.deleteRole(roleRequest);
    }

    @Override
    public ResponseEntity<Response> updateRole(RoleRequest roleRequest){
        Response response = new Response(false, roleService.updateRole(roleRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
