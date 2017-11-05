package dbManager;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface DbManager {

    void  add (String title, String date) throws SQLException;

    void remove (String title) throws SQLException;

    void edit (String title) throws SQLException;

    List<Book> allBooks () throws SQLException;
}
