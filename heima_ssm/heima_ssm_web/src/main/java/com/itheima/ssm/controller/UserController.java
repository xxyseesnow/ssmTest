package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    //用户详情
    @RequestMapping("/findById.do")
    //第3种权限控制,只有这种权限的人可以访问用户详情
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfoList = userService.findById(id);
        mv.addObject("user",userInfoList);
        mv.setViewName("user-show");
        return mv;
    }

    //添加用户
    @RequestMapping("/save.do")
    //第3种服务器端权限控制,只有admin可以保存user
    @PreAuthorize("authentication.principal.username=='admin'")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //删除用户
    @RequestMapping("/deleteUser.do")
    public String deleteUserById(Integer id) throws Exception {
        userService.deleteUserById(id);
        return "redirect:findAll.do";
    }

    //查询所有用户
    @RequestMapping("/findAll.do")
    //hejsr250有关的的权限控制
//    @RolesAllowed("USER")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    //查出可以添加的角色
    @RequestMapping("/addRoleByUserId.do")
    public ModelAndView addRoleByUserId(@RequestParam(required = true,name = "id") Integer id)throws Exception{

        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo =userService.findById(id);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.addRoleByUserId(id);
//        System.out.println(userInfo+userInfo.getUsername());
//        for(Role list: otherRoles){
//            System.out.println(list+list.getRoleName());
//        }

        //空格也算符号....不能运行是因为有空格
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
//        ModelAndView mv = new ModelAndView();
//        UserInfo userInfo = userService.addRoleByUserId(id);
//        mv.addObject("userInfo ",userInfo);
//        mv.setViewName("user-role-add");
//        return mv;
    }

    //添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,
                              @RequestParam(name = "ids",required = true) Integer[] rolesId)throws Exception{
        userService.addRoleToUser(userId,rolesId);
        return "redirect:findAll.do";
    }


}
