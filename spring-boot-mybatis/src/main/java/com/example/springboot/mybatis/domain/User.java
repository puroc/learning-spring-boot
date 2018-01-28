package com.example.springboot.mybatis.domain;

public class User {
    private Long id;

    private String name;

    private Long roleid;

    private Integer age;

    private Integer deleteflag;

    public User(Long id, String name, Long roleid, Integer age, Integer deleteflag) {
        this.id = id;
        this.name = name;
        this.roleid = roleid;
        this.age = age;
        this.deleteflag = deleteflag;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}