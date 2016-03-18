package com.mylibrary.impl;

import com.mylibrary.*;

import java.util.List;

public class UserService implements GenericService<User> {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void add(User object) throws PersistException {
        userDao.add(object);
    }

    @Override
    public User getById(Integer key) throws PersistException {
        return userDao.getById(key);
    }

    @Override
    public void update(User object) throws  PersistException {
        userDao.update(object);
    }

    @Override
    public void delete(User object) throws PersistException {
        userDao.delete(object);
    }

    @Override
    public List<User> getAll() throws PersistException {
        return userDao.getAll();
    }

    public User loadUserByUsername(String s) throws PersistException{
        return userDao.loadUserByUsername(s);
    }

    public Role getRoleById(Integer key) throws PersistException{
        return userDao.getRoleById(key);
    }

}
