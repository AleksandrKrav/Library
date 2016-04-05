import com.google.gson.Gson;
import com.mylibrary.Book;
import com.mylibrary.BookDao;
import com.mylibrary.PersistException;
import com.mylibrary.impl.BookDaoImpl;
import org.junit.Test;

/**
 * Created by Alexandr on 05.04.2016.
 */
public class BookDaoTest {
    @Test
    public void deleteBook() throws PersistException {
        BookDao bookDao = new BookDaoImpl();
        Book book = bookDao.getById(5);
        Gson gson = new Gson();
        System.out.println(book);
        String json = gson.toJson(book);
        System.out.println(json);
    }
}
