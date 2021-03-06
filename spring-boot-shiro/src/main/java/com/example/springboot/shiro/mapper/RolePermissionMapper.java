package com.example.springboot.shiro.mapper;

import com.example.springboot.shiro.entity.RolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    Set<String> findPermissions(Long id);
}
