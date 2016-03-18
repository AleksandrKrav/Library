package com.mylibrary.impl;

import com.mylibrary.PersistException;
import com.mylibrary.Role;
import com.mylibrary.UserDao;
import com.mylibrary.User;
import com.mylibrary.utils.HibernateUtil;
import org.hibernate.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private EntityManager manager;

    public void add(User object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(object);
        manager.getTransaction().commit();
        manager.close();
    }

    public User getById(Integer key) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        User user = manager.find(User.class, key);
        manager.close();
        return user;
    }

    public void update(User object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(object);
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(User object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(User.class, object.getId()));
        manager.getTransaction().commit();
        manager.close();
    }

    public List<User> getAll() throws PersistException {
        manager = HibernateUtil.getEntityManager();
        TypedQuery<User> namedQuery = manager.createQuery("SELECT u FROM User u ", User.class);
        List<User> users = namedQuery.getResultList();
        manager.close();
        return users;
    }

    @Override
    public User loadUserByUsername(String s) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        TypedQuery<User> q = manager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        q.setParameter("login", s);
        User user = (User) q.getSingleResult();
        manager.close();
        return user;
    }

    @Override
    public Role getRoleById(Integer key) throws PersistException{
        manager = HibernateUtil.getEntityManager();
        Role role = manager.find(Role.class, key);
        manager.close();
        return role;
    }
}
