package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPermissionByRoleId(Integer roleId)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Select("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission p)throws Exception;

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_permissionById(Integer id)throws Exception;

    @Delete("delete from Permission where id=#{id}")
    void deleteById(Integer id)throws Exception;

    @Select("select * from permission where id=#{id}")
    Permission findById(Integer id)throws Exception;
}
