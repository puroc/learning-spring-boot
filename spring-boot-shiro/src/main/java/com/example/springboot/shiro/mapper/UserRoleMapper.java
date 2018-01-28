package com.example.springboot.shiro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.springboot.shiro.entity.Role;
import com.example.springboot.shiro.entity.UserRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Role> findRoleListByUserId(long uid);

}
