package com.mylibrary;

public interface UserDao extends GenericDao<User> {
   User loadUserByUsername(String s) throws PersistException;
   Role getRoleById(Integer key) throws PersistException;
}
