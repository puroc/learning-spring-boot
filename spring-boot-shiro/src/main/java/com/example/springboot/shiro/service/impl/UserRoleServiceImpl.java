package com.example.springboot.shiro.service.impl;

import com.example.springboot.shiro.entity.UserRole;
import com.example.springboot.shiro.mapper.UserRoleMapper;
import com.example.springboot.shiro.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
