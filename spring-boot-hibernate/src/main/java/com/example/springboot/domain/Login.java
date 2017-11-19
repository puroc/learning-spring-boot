package com.example.springboot.domain;

public class Login {
    private String username;
    private String password;
    private static String tenantId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getTenantId() {
        return tenantId;
    }

    public static void setTenantId(String tenantId) {
        Login.tenantId = tenantId;
    }


}
