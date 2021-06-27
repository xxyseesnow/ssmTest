package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {


    @Autowired
    IRoleDao roleDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    //添加角色
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public void deleteRoleById(Integer id) throws Exception {
        //删除users_role表中的数据
        roleDao.deleteFromUsers_roleById(id);
        //删除role_permission表中的数据
        roleDao.deleteFromrole_permissionById(id);
        //删除role表中的数据
        roleDao.deleteRoleById(id);
    }

    @Override
    public Role findById(Integer id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> addPermissionByRoleId(Integer roleId) throws Exception {
        return roleDao.addPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionsId) throws Exception {
        for(Integer permissionId:permissionsId){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
