package com.mylibrary.servlets;

import com.mylibrary.Author;
import com.mylibrary.Book;
import com.mylibrary.Genre;
import com.mylibrary.PersistException;
import com.mylibrary.impl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@MultipartConfig
public class AddBookServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("book-add-button") != null) {
            Book book = new Book();
            Author author = new Author();
            author.setName(req.getParameter("author_name"));
            book.setName(req.getParameter("book_name"));
            book.setAuthor(author);
            Genre genre;
            final Part filePart = req.getPart("uploadFile");
            final String fileName = getFileName(filePart);

            OutputStream out = null;
            InputStream filecontent = null;
            try {
                book.setFileName(fileName);
                filecontent = filePart.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    book.setData(bytes);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (filecontent != null) {
                    filecontent.close();
                }
            }
            try {
                genre = bookService.genreGetById(Integer.valueOf(req.getParameter("genre-book")));
                book.setGenre(genre);
                book.setDescription(req.getParameter("book-description"));
                bookService.add(book);
            } catch (PersistException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/books");
        }
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
