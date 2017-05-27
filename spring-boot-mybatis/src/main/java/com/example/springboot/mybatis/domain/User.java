package com.example.springboot.mybatis.domain;

import java.util.Date;

public class User {
    private Long id;

    private Date createdate;

    private String name;

    private Long did;

    public User(Long id, Date createdate, String name, Long did) {
        this.id = id;
        this.createdate = createdate;
        this.name = name;
        this.did = did;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }
}