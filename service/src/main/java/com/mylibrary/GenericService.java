package com.mylibrary;

import com.mylibrary.PersistException;

import java.util.List;

public interface GenericService<T> {
    public void add(T object) throws PersistException;

    public T getById(Integer key) throws PersistException;

    public void update(T object) throws  PersistException;

    public void delete(T object) throws  PersistException;

    public List<T> getAll() throws PersistException;
}
