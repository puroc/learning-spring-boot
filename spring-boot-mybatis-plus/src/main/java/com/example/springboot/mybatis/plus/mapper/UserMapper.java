package com.example.springboot.mybatis.plus.mapper;

import com.example.springboot.mybatis.plus.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pud123
 * @since 2018-01-28
 */
public interface UserMapper extends BaseMapper<User> {

    int find(String name);

}
