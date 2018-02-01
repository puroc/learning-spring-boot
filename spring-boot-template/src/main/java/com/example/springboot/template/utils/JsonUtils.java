package com.example.springboot.template.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T toObj(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
