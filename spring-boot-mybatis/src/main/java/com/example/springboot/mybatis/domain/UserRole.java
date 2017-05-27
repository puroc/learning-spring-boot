package com.example.springboot.mybatis.domain;

public class UserRole {
    private Long userId;

    private Long rolesId;

    public UserRole(Long userId, Long rolesId) {
        this.userId = userId;
        this.rolesId = rolesId;
    }

    public UserRole() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }
}