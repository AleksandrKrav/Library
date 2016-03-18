import com.mylibrary.*;
import com.mylibrary.impl.BookDaoImpl;
import com.mylibrary.impl.UserDaoImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDaoImplTest {

    @Test
    public void testCreateUser(){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setName("Alex");
        Book book = new Book();
        book.setName("Kyiv and Polytics");

        Author author = new Author();
        author.setName("Pole");
        book.setAuthor(author);

        Genre genre = new Genre();
        genre.setName("horror");
        book.setGenre(genre);

        Set<Book> books = new HashSet<Book>();
        books.add(book);
//        user.setBooks(books);

        Role role = new Role();
        role.setName("Admin");

        Permission permission = new Permission();
        permission.setCanRead(true);
        permission.setCanDelete(true);
        permission.setCanEdit(true);
        role.setPermission(permission);
        user.setRole(role);

        try {
            userDao.add(user);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetByIdUser() throws PersistException {
        UserDao userDao = new UserDaoImpl();
        User u = userDao.loadUserByUsername("Alex23");
        System.out.println(u);
//        User user = userDao.getById(1);
//        System.out.println(user);
//        List<User> users = userDao.getAll();
//        for(User u : users){
//            System.out.println(u);
//        }
    }

    @Test
    public void testUpdateUser() throws PersistException {
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        Book book = bookDao.getById(1);
        System.out.println(book);
//        User user = userDao.loadUserByUsername("Alex");
//        System.out.println(user);
//        List<Book> books = user.getBooks();
//        for (Book b : books){
//            System.out.println(b);
//        }
//        books.add(book);
//        user.setBooks(books);
//        userDao.update(user);
    }

    @Test
    public void testGetAllUser() throws PersistException {
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getAll();
        for(User u : users){
            System.out.println(u);
        }
    }

    @Test
    public void testDeleteUser() throws PersistException {
        UserDao userDao = new UserDaoImpl();
        User u = userDao.getById(1);
//        Set<Book> books = u.getBooks();
//        for (Book b : books){
//            System.out.println(b);
//        }

    }
}
