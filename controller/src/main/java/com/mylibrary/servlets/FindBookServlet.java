package com.mylibrary.servlets;

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
import java.util.List;

@WebServlet(name = "FindBookServlet", urlPatterns = "/findBook")
public class FindBookServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("find") != null) {
            String bookName = req.getParameter("book-name");
            String authorName = req.getParameter("author-name");
            try {
                Genre genre = bookService.genreGetById(Integer.valueOf(req.getParameter("genre-book")));
                List<Book> books = bookService.getBooksByCriteria(bookName, authorName, genre);
                req.setAttribute("books", books);
                List<Genre> genres = bookService.getAllGenre();
                req.setAttribute("genres", genres);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }


}
