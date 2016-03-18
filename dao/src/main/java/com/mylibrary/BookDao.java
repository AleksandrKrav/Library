package com.mylibrary;

import java.util.List;

public interface BookDao extends GenericDao<Book> {

    List<Genre> getAllGenre() throws PersistException;

    Genre genreGetById(Integer key) throws PersistException;

    List<Book> getBooksByCriteria(String bookName, String authorName, Genre genre) throws PersistException;
}
