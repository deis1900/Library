import dbManager.DbManager;
import dbManager.DbManagerImpl;
import model.*;

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
                    System.out.println("Enter release_date. and author. to add:");
                    String info = scanner.nextLine();
                    String[] w = info.split(". ", 2);
                    dbManager.add(words[2], w[0], w[1], words[0]);
                } else {
                    System.out.println("You did not enter name. Repeat enter:");

                }
                break;
            case "edit":
                if (words.length > 2) {
                    System.out.println("Enter release_date. and author. to add:");
                    String info = scanner.nextLine();
                    String[] w = info.split(".", 0);
                    dbManager.edit(words[2], w[0], w[1], words[0]);
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
            case "list":
                char[] tableChoice = words[0].toCharArray();

                switch (tableChoice[0]) {
                    case 'b':
                        System.out.println("\n All Books:");
                        List<Model> bookList = dbManager.listAll("Book");
                        Book book;
                        for (Model model : bookList) {
                            book = new Book(model.getId(), model.getName(), model.getDate(), model.getAuthor());
                            System.out.println(book.toString());
                        }
                        break;
                    case 's':
                        System.out.println("\n All Songs:");
                        List<Model> songList = dbManager.listAll("Song");
                        Song song;
                        for (Model model : songList) {
                            song = new Song(model.getId(), model.getName(), model.getDate(), model.getAuthor());
                            System.out.println(song.toString());
                        }
                        break;
                    case 'm':
                        System.out.println("\n All Movies:");
                        List<Model> movieList = dbManager.listAll("Movie");
                        Movie movie;
                        for (Model model : movieList) {
                            movie = new Movie(model.getId(), model.getName(), model.getDate(), model.getAuthor());
                            System.out.println(movie.toString());
                        }
                        break;
                    case 'g':
                        System.out.println("\n All Games:");
                        List<Model> gameList = dbManager.listAll("Game");
                        Game game;
                        for (Model model: gameList) {
                            game = new Game(model.getId(), model.getName(), model.getDate(), model.getAuthor());
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
                " section(for example BOOK)" +
                    "\n\tadd      ..title..\n" +
                    "\tremove   ..title..\n" +
                    "\tedit     ..title..\n" +
                    "\tlist ..title..\n" +
                " help\n" +
                " quit");
    }
}
