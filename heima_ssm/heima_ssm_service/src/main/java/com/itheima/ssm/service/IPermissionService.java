package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.UserInfo;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll()throws Exception;

    void save(Permission p)throws Exception;

    void deleteById(Integer id)throws Exception;

    Permission findById(Integer id)throws Exception;
}
