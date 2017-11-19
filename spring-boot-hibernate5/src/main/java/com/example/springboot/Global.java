package com.example.springboot;

/**
 * Created by puroc on 2017/11/19.
 */
public class Global {

    private ThreadLocal<String> tenants = new ThreadLocal<String>();

    private static final Global global = new Global();

    private Global() {
    }

    public static final Global getInstance() {
        return global;
    }

    public ThreadLocal<String> getTenants() {
        return tenants;
    }
}
