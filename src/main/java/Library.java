
import dbManager.DbManager;
import dbManager.DbManagerImpl;
import model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        DbManager dbManager = new DbManagerImpl();
        String[] words;
        String str;


        do {
            System.out.println("Enter:");

            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();

            words = str.split(" ", 2);

            try {

                if (words[0].equals("add")) {
                    if (words.length > 1) {
                        System.out.println("Enter year of publication to add:");
                        String d = scanner.nextLine();
                        dbManager.add(words[1], d);
                    } else {
                        System.out.println("You did not enter name. Repeat enter:");
                        return;
                    }

                } else if (words[0].equals("edit")) {
                    if (words.length > 1) {
                        System.out.println("Enter year of publication to add:");
                        String date = scanner.nextLine();
                        dbManager.edit(words[1], date);
                    } else {
                        System.out.println("You did not enter name. Repeat enter:");
                        return;
                    }

                } else if (words[0].equals("remove")) {
                    if (words.length > 1) {
                        dbManager.remove(words[1]);
                    } else {
                        System.out.println("You did not enter name. Repeat enter:");
                        return;
                    }
                } else if (words[0].equals("all")) {
                    System.out.println("\n ALL Books:");
                    List<Book> bookList = dbManager.allBooks();
                    for (Book book : bookList) {
                        System.out.println(
                                " | " + book.getId() +
                                        " \t| " + book.getTitle() + " "
                                        + book.getDate());
                    }
                } else {
                    System.out.println("Invalid input.");
                    System.out.println("Enter method name and title:" +
                            "\n add  \t..title...." +
                            "\n edit \t..title..." +
                            "\n remove ..title.." +
                            "\n all books ..title.." +
                            "\n quite.");
                }

            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("\n To re-enter press 'Enter'");
            str = sc.nextLine();
        } while (!str.equals("quite"));
    }
}
