package dbManager;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManagerImpl implements DbManager {
    private static String connectionUrl = "jdbc:sqlite:MyLib.db";
    private Connection connection = null;
    private Statement stmt = null;

    private void open() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connectionUrl);
            connection.setAutoCommit(false);

            createBookTable();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createBookTable() throws Exception{
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS BOOK" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "TITLE  TEXT NOT NULL," +
                    "DATE   TEXT NOT NULL);";
            stmt.executeUpdate(sql);
            stmt.close();
    }

    private Boolean checkRow(String title){

        boolean b = false;
        try {
            String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ' " + title + " ';";
            b = stmt.execute(sql);
            System.out.println(b);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
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

    public void add(String  title, String date) throws SQLException {
        open();
        stmt = connection.createStatement();
        if (checkRow(title)) {
            String sql = "INSERT INTO BOOK (TITLE, DATE) " +
                    " VALUES (' " + title + " ', + ' " + date + " ');";
            stmt.executeUpdate(sql);
            System.out.println("book " + title + " added");
        }else {
            System.out.println("There is no such record in the table");
        }

        close();

    }

    public void remove(String title) throws SQLException {

        open();
        if (checkRow(title)) {
            stmt = connection.createStatement();
            String sql = "DELETE from BOOK where TITLE = '" + title + "';";
            stmt.executeUpdate(sql);
            connection.commit();
            System.out.println("book " + title + " was removed.");
        }else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public void edit(String title) throws SQLException {

        open();
        stmt = connection.createStatement();

        if (checkRow(title)) {
            String sql = "UPDATE BOOK SET date = 2011 WHERE TITLE= ' " + title + " '; ";
            stmt.executeUpdate(sql);
            connection.commit();

            System.out.println("book " + title + " was edited.");
        }else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public List<Book> allBooks() throws SQLException {

        open();

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM BOOK;" );
            List<Book> booksList = new ArrayList<Book>();

            while ( rs.next() ) {
                Book b = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
                booksList.add(b);
            }

        close();
        return booksList;
    }
}
