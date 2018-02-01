package com.example.springboot.template.table;


import com.example.springboot.template.domain.User;
import com.example.springboot.template.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class TableData<T> {

    private List<T> data = new ArrayList<T>();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public static void main(String[] args) {
        User u1 = new User();
        u1.setId((long) 1);
        u1.setName("zhangsan");
        u1.setPassword("123");
        u1.setPhone("188");
        User u2 = new User();
        u2.setId((long) 2);
        u2.setName("lisi");
        u2.setPassword("456");
        u2.setPhone("138");
        TableData<User> td = new TableData<User>();
        td.getData().add(u1);
        td.getData().add(u2);
        System.out.println(td.toJson());

    }

}
