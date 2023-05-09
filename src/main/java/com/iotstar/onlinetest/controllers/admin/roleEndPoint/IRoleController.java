package com.iotstar.onlinetest.controllers.admin.roleEndPoint;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/role")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
public interface IRoleController {
    @GetMapping({"/", ""})
    ResponseEntity<?> getRoles(@RequestParam(required = false, defaultValue = "0") int index,
                                      @RequestParam(required = false, defaultValue = "20") int size);

    @PostMapping({"", "/"})
    ResponseEntity<?> getRoleByRoleName(@RequestBody RoleRequest roleRequest);

    @PostMapping("/add")
    void addRole(@RequestBody RoleRequest roleRequest);

    @PostMapping("/del")
    void delRole(@RequestBody RoleRequest roleRequest);

    @PostMapping("/update")
    ResponseEntity<Response> updateRole(@RequestBody RoleRequest roleRequest);

}
