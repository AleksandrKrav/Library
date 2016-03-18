import com.mylibrary.*;
import com.mylibrary.impl.BookDaoImpl;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class BookDaoImplTest {

    @Test
    public void testCreateUser() throws PersistException {
        BookDao bookDao = new BookDaoImpl();
        Book book = new Book();
        book.setName("asdf");
//        book.setAuthor(author);
        Genre genre;
//        final Part filePart = req.getPart("file");
//        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        book.setFileName("fileName");
//            filecontent =  filePart.getInputStream();
//            int read = 0;
//            final byte[] bytes = new byte[1024];
//
//            while ((read = filecontent.read(bytes)) != -1) {
//                book.setData();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (filecontent != null) {
//                filecontent.close();
//            }
//        }
//        try {
        genre = bookDao.genreGetById(1);
        book.setGenre(genre);
        book.setDescription("book-description");
        bookDao.add(book);
    }

    @Test
    public void testGetByIdBook() throws PersistException {
        BookDao bookDao = new BookDaoImpl();
        List<Book> bookLit = bookDao.getBooksByCriteria("asdf", "",bookDao.genreGetById(1));
        for(Book b : bookLit){
            System.out.println(b);
        }
//        User user = userDao.getById(1);
//        System.out.println(user);
//        List<User> users = userDao.getAll();
//        for(User u : users){
//            System.out.println(u);
//        }
    }

}
