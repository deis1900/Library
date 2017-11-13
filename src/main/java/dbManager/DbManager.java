package dbManager;

import model.Book;
import model.Game;
import model.Movie;
import model.Song;

import java.sql.SQLException;
import java.util.List;

public interface DbManager {

    void  add (String name, String date, String  tableName) throws SQLException;

    void remove (String name, String  tableName) throws SQLException;

    void edit (String name, String date, String  tableName) throws SQLException;

    List<Book> allBooks () throws SQLException;

    List<Song> allSongs () throws SQLException;

    List<Movie> allMovies () throws SQLException;

    List<Game> allGames () throws SQLException;
}
