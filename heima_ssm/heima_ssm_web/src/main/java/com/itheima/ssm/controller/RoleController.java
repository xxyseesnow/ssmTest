package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("/findAll.do")
    //和2方法级权限控制有关 自带的,和jsr250相比要带ROLE_的前缀
//    @Secured("ROLE_USER")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role)throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) Integer id)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRoleById.do")
    public String deleteRoleById(Integer id)throws Exception{
        roleService.deleteRoleById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/addPermissionByRoleId.do")
    public ModelAndView addPermission(@RequestParam(name = "id",required = true)Integer roleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.addPermissionByRoleId(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name ="roleId",required = true)Integer roleId,
                                      @RequestParam(name = "ids",required = true)Integer[] permissionsId)throws Exception{
        roleService.addPermissionToRole(roleId,permissionsId);
        return "redirect:findAll.do";
    }

}
