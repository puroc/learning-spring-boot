package com.example.springboot.mybatis.plus.service.impl;

import com.example.springboot.mybatis.plus.entity.Role;
import com.example.springboot.mybatis.plus.mapper.RoleMapper;
import com.example.springboot.mybatis.plus.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pud123
 * @since 2018-01-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
