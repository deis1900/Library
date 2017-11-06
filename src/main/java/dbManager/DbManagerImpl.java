package dbManager;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManagerImpl implements DbManager {
    private static final String CONNECTIONURL = "jdbc:sqlite:MyLib.db";
    private Connection connection = null;
    private Statement stmt = null;

    private void open() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONURL);

            createBookTable();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    private void createBookTable() throws SQLException {
        stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS BOOK" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "TITLE  TEXT NOT NULL," +
                "DATE   TEXT);";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private Boolean checkRow(String title) {

        boolean b = true;
        try {
            String sql = "SELECT * FROM BOOK;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("TITLE").equals(title)) {
                    b = false;
                }
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return b;
    }

    private void close() {
        try {

            if (connection != null) {
                stmt.close();
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void add(String title, String date) throws SQLException {
        open();
        stmt = connection.createStatement();
        if (checkRow(title)) {
            String sql = "INSERT INTO BOOK (TITLE, DATE) " +
                    " VALUES (' " + title + " ', ' " + date + " ');";
            stmt.executeUpdate(sql);
            System.out.println("book " + title + " added");

        } else {
            System.out.println("In the table there is such record");
        }

        close();

    }

    public void remove(String title) throws SQLException {

        open();
        if (checkRow(title)) {
            stmt = connection.createStatement();
            String sql = "DELETE FROM BOOK WHERE TITLE = ' " + title + " ';";
            stmt.executeUpdate(sql);
            System.out.println("book " + title + " was removed.");
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public void edit(String title, String date) throws SQLException {

        open();
        stmt = connection.createStatement();

        if (checkRow(title)) {
            String sql = "UPDATE BOOK SET DATE = ' " + date + " '" +
                    "WHERE TITLE= ' " + title + " '; ";
            stmt.executeUpdate(sql);

            System.out.println("book " + title + " was edited.");
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public List<Book> allBooks() throws SQLException {

        open();

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
        List<Book> booksList = new ArrayList<Book>();

        while (rs.next()) {
            Book b = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
            booksList.add(b);
        }

        close();
        return booksList;
    }
}
