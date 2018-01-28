package com.example.springboot.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.springboot.shiro.entity.Role;
import com.example.springboot.shiro.entity.User;
import com.example.springboot.shiro.mapper.RolePermissionMapper;
import com.example.springboot.shiro.mapper.UserMapper;
import com.example.springboot.shiro.mapper.UserRoleMapper;
import com.example.springboot.shiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> findRolePermissions(long uid) {
        List<Role> roleIdList = userRoleMapper.findRoleListByUserId(uid);
        for (Role role : roleIdList) {
            Set<String> everyRolePer = rolePermissionMapper.findPermissions(role.getId());
            role.setPerNameSet(everyRolePer);
        }
        return roleIdList;
    }
}
