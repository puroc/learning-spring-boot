package com.example.spring.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by puroc on 16/10/23.
 */
@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;//主键.

    private String name;//测试名称.

}
