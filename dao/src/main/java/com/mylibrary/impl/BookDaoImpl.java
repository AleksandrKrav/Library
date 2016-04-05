package com.mylibrary.impl;

import com.mylibrary.*;
import com.mylibrary.utils.HibernateUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private EntityManager manager;

    @Override
    public void add(Book object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Book getById(Integer key) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        Book book = manager.find(Book.class, key);
        manager.close();
        return book;
    }

    @Override
    public void update(Book object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(object);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void delete(Book object) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(Book.class, object.getId()));
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List<Book> getAll() throws PersistException {
        manager = HibernateUtil.getEntityManager();
        TypedQuery<Book> namedQuery = manager.createQuery("SELECT u FROM Book u ", Book.class);
        List<Book> books = namedQuery.getResultList();
        manager.close();
        return books;
    }

    @Override
    public List<Genre> getAllGenre() throws PersistException {
        manager = HibernateUtil.getEntityManager();
        TypedQuery<Genre> namedQuery = manager.createQuery("SELECT g FROM Genre g ", Genre.class);
        List<Genre> genres = namedQuery.getResultList();
        manager.close();
        return genres;
    }

    @Override
    public Genre genreGetById(Integer key) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        Genre genre = manager.find(Genre.class, key);
        manager.close();
        return genre;
    }

    @Override
    public List<Book> getBooksByCriteria(String bookName, String authorName, Genre genre) throws PersistException {
        manager = HibernateUtil.getEntityManager();
        boolean isBookEmpty = bookName.equals("");
        boolean isAuthorEmpty = authorName.equals("");
        if (!isBookEmpty && !isAuthorEmpty) {
            TypedQuery<Book> namedQuery = manager.createQuery("SELECT b FROM Book b" +
                    " WHERE b.name = :book_name and b.author.name  = :author_name and b.genre.id = :genre_id", Book.class);
            namedQuery.setParameter("book_name", bookName);
            namedQuery.setParameter("author_name", authorName);
            namedQuery.setParameter("genre_id", new Integer(genre.getId()));
            List<Book> books = namedQuery.getResultList();
            manager.close();
            return books;
        } else if ((isBookEmpty && !isAuthorEmpty) || (!isBookEmpty && isAuthorEmpty)) {
            if (isAuthorEmpty) {
                TypedQuery<Book> namedQuery = manager.createQuery("SELECT b FROM Book b" +
                        " WHERE b.name = :book_name and b.genre.id  = :genre_id", Book.class);
                namedQuery.setParameter("book_name", bookName);
                namedQuery.setParameter("genre_id", new Integer(genre.getId()));
                List<Book> books = namedQuery.getResultList();
                manager.close();
                return books;
            } else {
                TypedQuery<Book> namedQuery = manager.createQuery("SELECT b FROM Book b" +
                        " WHERE b.genre.id  = :genre_id and b.author.name  = :author_name", Book.class);
                namedQuery.setParameter("author_name", authorName);
                namedQuery.setParameter("genre_id", new Integer(genre.getId()));
                List<Book> books = namedQuery.getResultList();
                manager.close();
                return books;
            }
        } else {
            TypedQuery<Book> namedQuery = manager.createQuery("SELECT b FROM Book b" +
                    " WHERE b.genre.id  = :genre_id", Book.class);
            namedQuery.setParameter("genre_id", new Integer(genre.getId()));
            List<Book> books = namedQuery.getResultList();
            manager.close();
            return books;
        }
    }
}
