package com.mylibrary.servlets;

import com.mylibrary.Author;
import com.mylibrary.Book;
import com.mylibrary.Genre;
import com.mylibrary.PersistException;
import com.mylibrary.impl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditBookServlet", urlPatterns = "/editBook")
public class EditBookservlet extends HttpServlet {

    private BookService bookService;
    private Book book;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            book = bookService.getById(id);
            req.setAttribute("book_name", book.getName());
            req.setAttribute("author", book.getAuthor().getName());
            req.setAttribute("genres", bookService.getAllGenre());
            req.setAttribute("description", book.getDescription());
            req.getRequestDispatcher("/pages/editBook.jsp").forward(req, resp);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("book-edit-button") != null) {
            book.setName(req.getParameter("book_name_edit"));
            Author author = book.getAuthor();
            author.setName(req.getParameter("edit_author_name"));
            book.setAuthor(author);
            Genre genre;
            try {
                genre = bookService.genreGetById(Integer.valueOf(req.getParameter("edit-genre-book")));
                book.setGenre(genre);
                book.setDescription(req.getParameter("edit-book-description"));
                bookService.update(book);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("book-remove-button") != null) {
            try {
                System.out.println("remove");
                bookService.delete(book);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/books");
    }
}
