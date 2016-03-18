package com.mylibrary.impl;

import com.mylibrary.*;

import java.util.List;

public class BookService implements GenericService<Book> {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void add(Book object) throws PersistException {
        bookDao.add(object);
    }

    @Override
    public Book getById(Integer key) throws PersistException {
        return bookDao.getById(key);
    }

    @Override
    public void update(Book object) throws PersistException {
        bookDao.update(object);
    }

    @Override
    public void delete(Book object) throws PersistException {
        bookDao.delete(object);
    }

    @Override
    public List<Book> getAll() throws PersistException {
        return bookDao.getAll();
    }

    public List<Genre> getAllGenre() throws PersistException {
        return bookDao.getAllGenre();
    }

    public Genre genreGetById(Integer key) throws PersistException {
        return bookDao.genreGetById(key);
    }

    public List<Book> getBooksByCriteria(String bookName, String authorName, Genre genre) throws PersistException{
        return bookDao.getBooksByCriteria(bookName, authorName, genre);
    }

}
