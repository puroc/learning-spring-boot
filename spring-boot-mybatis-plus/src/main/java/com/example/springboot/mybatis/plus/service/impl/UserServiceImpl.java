package com.example.springboot.mybatis.plus.service.impl;

import com.example.springboot.mybatis.plus.entity.User;
import com.example.springboot.mybatis.plus.mapper.UserMapper;
import com.example.springboot.mybatis.plus.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pud123
 * @since 2018-01-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
