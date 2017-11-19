package com.example.springboot;

import com.example.springboot.domain.Guest;
import com.example.springboot.domain.Login;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by puroc on 2017/11/19.
 */
@Component
public class Test implements CommandLineRunner {


    private SessionBuilder<SessionBuilder> sessionFactory;

    @Override
    public void run(String... strings) throws Exception {
        Session session = null;
        Guest guest = null;
        List<Guest> list = null;
        Transaction tx = null;
        System.out.println("======== 租户 hotel_1 ========");
        Login.setTenantId("hotel_1");
        //加载核心配置文件
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        //得到session工厂(XML文件配置)
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        guest = new Guest();
        guest.setId(1);
        guest.setName("张三");
        guest.setTelephone("56785678");
        guest.setAddress("上海市张扬路88号");
        guest.setEmail("zhangsan@gmail.com");
        session.saveOrUpdate(guest);
        list = session.createCriteria(Guest.class).list();
        for (Guest gue : list) {
            System.out.println("====" + gue.getId());
            System.out.println("====" + gue.getName());
            System.out.println("====" + gue.getEmail());
            System.out.println("====" + gue.getTelephone());
            System.out.println("====" + gue.getAddress());
        }
        tx.commit();
        session.close();
        System.out.println("======== 租户 hotel_2 ========");
        Login.setTenantId("hotel_2");
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        guest = new Guest();
        guest.setId(1);
        guest.setName("李四");
        guest.setTelephone("23452345");
        guest.setAddress("上海市南京路100号");
        guest.setEmail("lisi@gmail.com");
        session.saveOrUpdate(guest);
        list = session.createCriteria(Guest.class).list();
        for (Guest gue : list) {
            System.out.println("====" + gue.getId());
            System.out.println("====" + gue.getName());
            System.out.println("====" + gue.getEmail());
            System.out.println("====" + gue.getTelephone());
            System.out.println("====" + gue.getAddress());
        }
        tx.commit();
        session.close();
    }
}
