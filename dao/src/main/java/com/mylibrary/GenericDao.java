package com.mylibrary;

import java.util.List;

public interface GenericDao<T> {

    public void add(T object)  throws PersistException;

    public T getById(Integer key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;
}
