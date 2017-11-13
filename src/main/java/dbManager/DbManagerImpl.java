package dbManager;

import model.Book;
import model.Game;
import model.Movie;
import model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManagerImpl implements DbManager {
    private static final String CONNECTIONURL = "jdbc:sqlite:MyLib.db";
    private Connection connection = null;
    private Statement stmt = null;

    private void open(String tableName) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONURL);

            createTable(tableName);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    private void createTable(String tableName) throws SQLException {
                stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "TITLE  TEXT NOT NULL," +
                "DATE   TEXT);";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private Boolean checkRow(String title, String tableName) {

        boolean b = true;
        try {
            String sql = "SELECT * FROM " + tableName + ";";
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

    public void add(String title, String date, String tableName) throws SQLException {
        open(tableName);
        stmt = connection.createStatement();
        if (checkRow(title, tableName)) {
            String sql = "INSERT INTO " + tableName + " (TITLE, DATE) " +
                    " VALUES (' " + title + " ', ' " + date + " ');";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + title + " added");
            }else {
                System.out.println(tableName + " row not added");
            }

        } else {
            System.out.println("In the table there is such record");
        }

        close();

    }

    public void remove(String title, String tableName) throws SQLException {

        open(tableName);
        if (checkRow(title, tableName)) {
            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName + " WHERE TITLE = ' " + title + " ';";
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                System.out.println(tableName + " " + title + " was removed.");
            }else {
                System.out.println(tableName + " row not removed");
            }
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public void edit(String title, String date, String tableName) throws SQLException {

        open(tableName);
        stmt = connection.createStatement();

        if (checkRow(title, tableName)) {
            String sql = "UPDATE " + tableName + " SET DATE = ' " + date + " '" +
                    "WHERE TITLE= ' " + title + " '; ";
            int rows = stmt.executeUpdate(sql);
            if (rows ==1){
                System.out.println(tableName + " " + title + " was edited.");
            }else {
                System.out.println(tableName +  " row not edited");
            }
        } else {
            System.out.println("There is no such record in the table");
        }

        close();
    }

    public List<Book> allBooks() throws SQLException {
        String tableName = "BOOK";
        open(tableName);

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
        List<Book> booksList = new ArrayList<>();

        while (rs.next()) {
            Book b = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
            booksList.add(b);
        }

        close();
        return booksList;
    }
    public List<Song> allSongs() throws SQLException {
        String tableName = "song";
        open(tableName);

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM song;");
        List<Song> songsList = new ArrayList<>();

        while (rs.next()) {
            Song b = new Song(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
            songsList.add(b);
        }

        close();
        return songsList;
    }
    public List<Movie> allMovies() throws SQLException {
        String tableName = "movie";
        open(tableName);

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM movie;");
        List<Movie> moviesList = new ArrayList<>();

        while (rs.next()) {
            Movie m = new Movie(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
            moviesList.add(m);
        }

        close();
        return moviesList;
    }
    public List<Game> allGames() throws SQLException {
        String tableName = "game";
        open(tableName);

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM game;");
        List<Game> gamesList = new ArrayList<>();

        while (rs.next()) {
            Game g = new Game(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("DATE"));
            gamesList.add(g);
        }

        close();
        return gamesList;
    }
}
