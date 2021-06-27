package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;
    public List<Permission> findAll() throws Exception{
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission p) throws Exception {
        permissionDao.save(p);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        permissionDao.deleteFromRole_permissionById(id);
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(Integer id) throws Exception{
       return permissionDao.findById(id);
    }
}
