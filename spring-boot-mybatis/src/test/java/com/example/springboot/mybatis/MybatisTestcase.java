package com.example.springboot.mybatis;

import com.example.springboot.mybatis.domain.User;
import com.example.springboot.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTestcase {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey((long) 9);
        Assert.notNull(user.getName());
    }

    @Test
    public void deleteByPrimaryKey() {
//        userMapper.insert(new User() {{
//            this.setId((long) 9);
//            this.setName("333");
//        }});
//        userMapper.
//        userMapper.deleteByPrimaryKey((long) id);
//        Assert.isNull(userMapper.selectByPrimaryKey((long) id));
    }

    @Test
    //insert语句中包含该表的所有字段，不管这些字段是否为null
    public void insert() {
        userMapper.insert(new User() {{
            this.setName("2222");
        }});
    }

    @Test
    //插入的对象中哪些字段不为null，则insert语句中就包括哪些字段
    public void insertSelective() {
        userMapper.insertSelective(new User() {{
            this.setName("1111");
        }});
    }
}
