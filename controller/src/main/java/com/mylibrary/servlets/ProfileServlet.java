package com.mylibrary.servlets;

import com.mylibrary.Book;
import com.mylibrary.PersistException;
import com.mylibrary.User;
import com.mylibrary.impl.BookService;
import com.mylibrary.impl.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private BookService bookService;
    private User user;
    private static final int BUFFER_SIZE = 10240;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("download") != null) {
            int id = Integer.parseInt(req.getParameter("book_id"));
            try {
                Book book = bookService.getById(id);
                ServletContext context = getServletContext();
                String mimeType = context.getMimeType(book.getFileName());
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }
                System.out.println("MIME type: " + mimeType);

                resp.setContentType(mimeType);
                resp.setBufferSize(BUFFER_SIZE);
                resp.setHeader("Content-disposition", "attachment; filename=\"" + book.getFileName() + "\"");
                ServletOutputStream outStream = resp.getOutputStream();
                InputStream inputStream = new ByteArrayInputStream(book.getData());
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.flush();
                inputStream.close();
                outStream.close();
            } catch (PersistException e) {
                e.printStackTrace();
            }


        } else {
            String userName;
            String login = getPrincipal();
            List<Book> books = new ArrayList<>();
            try {
                user = userService.loadUserByUsername(login);
                userName = user.getName();
                books = user.getBooks();
                req.setAttribute("user_name", userName);
                req.setAttribute("books", books);
                req.getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
            } catch (PersistException e) {
                e.printStackTrace();
            }
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