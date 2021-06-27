package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImp implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserInfo userInfo=null;
        try{
            userInfo = userDao.findByUserName(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        //处理自己的用户对象封装成UserDetails
//        User user =new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));

        //状态0代表不能登录，1可用
//        User user =new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus()==0?false:true, true,true,true,getAuthority(userInfo.getRoles()));
        User user =new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,
                true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list =new ArrayList<>();
        for(Role role:roles)
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        return list;
    }


    //查询所有用户
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //用户详情
    @Override
    public UserInfo findById(Integer id) throws Exception{
        return userDao.findById(id);
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        userDao.deleteUserFromUsers_RoleByUserId(id);
        userDao.deleteUserById(id);
    }

    @Override
    public List<Role> addRoleByUserId(Integer id) throws Exception {
        return userDao.addRoleByUserId(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] rolesId) throws Exception {
        for(Integer roleId: rolesId){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
