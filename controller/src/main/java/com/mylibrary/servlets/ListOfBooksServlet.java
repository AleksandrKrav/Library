package com.mylibrary.servlets;

import com.mylibrary.Genre;
import com.mylibrary.User;
import com.mylibrary.impl.BookService;
import com.mylibrary.Book;
import com.mylibrary.PersistException;
import com.mylibrary.impl.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ListOfBooksServlet extends HttpServlet {

    private BookService bookService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Book> books = bookService.getAll();
            req.setAttribute("books", books);
            List<Genre> genres = bookService.getAllGenre();
            req.setAttribute("genres", genres);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("book_id"));
        try {
            Book book = bookService.getById(id);
            User user = userService.loadUserByUsername(getPrincipal());
            List<Book> books = user.getBooks();
            books.add(book);
            user.setBooks(books);
            userService.update(user);
            resp.sendRedirect("/profile");
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    private String getPrincipal() {
        String userLogin;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userLogin = ((UserDetails) principal).getUsername();
        } else {
            userLogin = principal.toString();
        }
        return userLogin;
    }
}
