package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService{
    public void createRole(RoleRequest roleRequest);

    public RoleResponse updateRole(RoleRequest roleRequest);

    public void deleteRole(RoleRequest roleRequest);

    public List<RoleResponse> getAllRole();

    public RoleResponse getRoleByRoleName(String roleName);
    public boolean existByRoleName (String roleName);
}
