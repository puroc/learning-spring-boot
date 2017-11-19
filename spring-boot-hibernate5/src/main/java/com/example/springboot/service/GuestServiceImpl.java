package com.example.springboot.service;

import com.example.springboot.dao.GuestDao;
import com.example.springboot.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("guestService")
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestDao guestDao;

    @Override
    public Guest load(String id) {
        return null;
    }

    @Override
    public Guest get(String id) {
        return guestDao.get(id);
    }

    @Override
    public List<Guest> findAll() {
        return guestDao.findAll();
    }

    @Override
    public void persist(Guest entity) {

    }

    @Override
    public int save(Guest entity) {
        return guestDao.save(entity);
    }

    @Override
    public void saveOrUpdate(Guest entity) {

    }

    @Override
    public void delete(String id) {
        guestDao.delete(id);

    }

    @Override
    public void flush() {

    }

}