package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role)throws Exception;

    void deleteRoleById(Integer id)throws Exception;

    Role findById(Integer id)throws Exception;

    List<Permission> addPermissionByRoleId(Integer roleId)throws Exception;

    void addPermissionToRole(Integer roleId, Integer[] permissionsId)throws Exception;
}
