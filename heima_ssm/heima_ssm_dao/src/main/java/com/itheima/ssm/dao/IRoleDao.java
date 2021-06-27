package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户Id查询出所有的角色
    @Select("select * from Role Where id in (select roleId from users_role where userId= #{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    public List<Role> findRoleByUserId(Integer userId)throws Exception;

    @Select("select * from role")
    List<Role> findAll()throws Exception;

    @Select("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Delete("delete from users_role where roleId=#{id}")
    void deleteFromUsers_roleById(Integer id)throws Exception;

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteFromrole_permissionById(Integer id)throws Exception;

    @Delete("delete from role where id=#{id}")
    void deleteRoleById(Integer id)throws Exception;

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    Role findById(Integer id)throws Exception;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> addPermissionByRoleId(Integer roleId)throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId)throws Exception;

//    @Select("select * form role where id not in (select roleId from users_role where roleId = #{roleId}")
//    List<Role> findNoRoleByUserId(Integer roleId)throws Exception;
}
