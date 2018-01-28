package com.example.springboot.mybatis.plus;

import com.baomidou.mybatisplus.mapper.Condition;
import com.example.springboot.mybatis.plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void count() {
		List list = userMapper.selectList(Condition.create().eq("name", "zhangsan"));
		Assert.assertTrue(list.size()==1);
	}

	@Test
	public void find() {
		int id = userMapper.find("zhangsan");
		System.out.println(id);
		Assert.assertTrue(id==2);
	}
}
