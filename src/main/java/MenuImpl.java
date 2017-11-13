import dbManager.DbManager;
import dbManager.DbManagerImpl;
import model.Book;
import model.Game;
import model.Movie;
import model.Song;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

class MenuImpl {
    private Scanner scanner = new Scanner(System.in);
    private DbManager dbManager = new DbManagerImpl();

    void choice (String str) throws SQLException{

        String[] words = str.split(" ", 3);
        char[] tableChoice = words[0].toCharArray();

            if (words[0].equals("book") ||
                    words[0].equals("song") ||
                    words[0].equals("movie") ||
                    words[0].equals("game") ||
                    words[0].equals("help") ||
                    words[0].equals("quit")) {

                switch (tableChoice[0]) {

                    case 'b':
                        choiceMethod(words);
                        break;
                    case 's':
                        choiceMethod(words);
                        break;
                    case 'm':
                        choiceMethod(words);
                        break;
                    case 'g':
                        choiceMethod(words);
                        break;
                    case 'h':
                        help();
                        break;
                    case 'q':
                        System.exit(0);
                   }
            }else {
                System.out.println("The table does not exist.");
                help();
            }
   }

    private void choiceMethod (String[] words) throws SQLException {

        switch (words[1]) {
            case "add":
                if (words.length > 2) {
                    System.out.println("Enter year of publication to add:");
                    String date = scanner.nextLine();
                    dbManager.add(words[2], date, words[0]);
                } else {
                    System.out.println("You did not enter name. Repeat enter:");

                }
                break;
            case "edit":
                if (words.length > 2) {
                    System.out.println("Enter year of publication to add:");
                    String date = scanner.nextLine();
                    dbManager.edit(words[2], date, words[0]);
                } else {
                    System.out.println("You did not enter name. Repeat enter:");
                }

                break;
            case "remove":
                if (words.length > 2) {
                    dbManager.remove(words[2], words[0]);
                } else {
                    System.out.println("You did not enter name. Repeat enter:");

                }
                break;
            case "all":
                char[] tableChoice = words[0].toCharArray();

                switch (tableChoice[0]) {
                    case 'b':
                        System.out.println("\n All Books:");
                        List<Book> bookList = dbManager.allBooks();
                        for (Book book : bookList) {
                            System.out.println(book.toString());
                        }
                        break;
                    case 's':
                        System.out.println("\n All Songs:");
                        List<Song> songList = dbManager.allSongs();
                        for (Song song : songList) {
                            System.out.println(song.toString());
                        }
                        break;
                    case 'm':
                        System.out.println("\n All Movies:");
                        List<Movie> movieList = dbManager.allMovies();
                        for (Movie movie : movieList) {
                            System.out.println(movie.toString());
                        }
                        break;
                    case 'g':
                        System.out.println("\n All Games:");
                        List<Game> gameList = dbManager.allGames();
                        for (Game game : gameList) {
                            System.out.println(game.toString());
                        }
                        break;
                }
                break;
            default:
                System.out.println("Invalid input.");
                help();
                break;
        }
    }

    private void help(){
        System.out.println("Please, make the correct enter:\n" +
                " book" +
                    "\n\tadd      ..title..\n" +
                    "\tremove   ..title..\n" +
                    "\tedit     ..title..\n" +
                    "\tall books ..title..\n" +
                " song" +
                    "\n\tadd      ..title..\n" +
                    "\tremove   ..title..\n" +
                    "\tedit     ..title..\n" +
                    "\tall songs ..title..\n" +
                " movie" +
                    "\n\tadd      ..title..\n" +
                    "\tremove   ..title..\n" +
                    "\tedit     ..title..\n" +
                    "\tall movies ..title..\n" +
                " game" +
                    "\n\tadd      ..title..\n" +
                    "\tremove   ..title..\n" +
                    "\tedit     ..title..\n" +
                    "\tall games ..title..\n" +
                " help\n" +
                " quit");
    }
}
