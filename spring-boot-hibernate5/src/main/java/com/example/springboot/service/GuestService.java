package com.example.springboot.service;

import com.example.springboot.domain.Guest;

import java.util.List;

public interface GuestService {

    Guest load(String id);

    Guest get(String id);

    List<Guest> findAll();

    void persist(Guest entity);

    int save(Guest entity);

    void saveOrUpdate(Guest entity);

    void delete(String id);

    void flush();
}