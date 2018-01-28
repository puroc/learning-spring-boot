package com.example.springboot.shiro.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.springboot.shiro.entity.Role;
import com.example.springboot.shiro.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
public interface IUserService extends IService<User> {

    List<Role> findRolePermissions(long uid);

}
