package com.example.springboot.dao;

import com.example.springboot.domain.Guest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("guestDao")
@Transactional
public class GuestDaoImpl implements GuestDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Guest load(String id) {
        return null;
    }

    @Override
    public Guest get(String id) {
        return (Guest) currentSession().get(Guest.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Guest> findAll() {
        List<Guest> users = currentSession().createQuery("from Guest guest").list();
        return users;
    }

    @Override
    public void persist(Guest entity) {

    }

    @Override
    public int save(Guest entity) {
        return (int) currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(Guest entity) {

    }

    @Override
    public void delete(String id) {
        currentSession().delete(currentSession().get(Guest.class, id));

    }

    @Override
    public void flush() {

    }


}