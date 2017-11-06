package dbManager;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface DbManager {

    void  add (String name, String date) throws SQLException;

    void remove (String name) throws SQLException;

    void edit (String name, String date) throws SQLException;

    List<Book> allBooks () throws SQLException;
}
