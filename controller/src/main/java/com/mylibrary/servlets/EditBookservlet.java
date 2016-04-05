package com.mylibrary.servlets;

import com.google.gson.Gson;
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

@WebServlet(name = "EditBookServlet", urlPatterns = "/editBookServlet")
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
            System.out.println(book);
            String json = new Gson().toJson(book);
            System.out.println(json);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("polipo");
        resp.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isRefresh = req.getParameter("button").equals("Refresh");
        if (isRefresh) {
            try {
                book = bookService.getById(id);
                book.setName(req.getParameter("book_edit_name"));
                Author author = book.getAuthor();
                author.setName(req.getParameter("author_edit_name"));
                book.setAuthor(author);
                Genre genre;
                genre = bookService.genreGetById(Integer.valueOf(req.getParameter("genre_edit_book")));
                book.setGenre(genre);
                book.setDescription(req.getParameter("book_edit_description"));
                System.out.println(book);
                bookService.update(book);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        } else  {
            try {
                book = bookService.getById(id);
                bookService.delete(book);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }
}
