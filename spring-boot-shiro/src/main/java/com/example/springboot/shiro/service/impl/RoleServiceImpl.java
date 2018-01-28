package com.example.springboot.shiro.service.impl;

import com.example.springboot.shiro.entity.Role;
import com.example.springboot.shiro.mapper.RoleMapper;
import com.example.springboot.shiro.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
