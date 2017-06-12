package com.example.springboot.mybatis;

import com.example.springboot.mybatis.mapper.UserMapper;
import com.example.springboot.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by puroc on 17/5/26.
 */
@Component
public class TestService implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("bb");
//        final User user = userMapper.selectByPrimaryKey((long) 1);
//        System.out.println(user.getName());
        userMapper.insert(new User(){{
            this.setName("2222");
        }});
        System.out.println("aaa");
    }
}
