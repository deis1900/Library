
import dbManager.DbManager;
import dbManager.DbManagerImpl;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        DbManager dbManager = new DbManagerImpl();

        try {

            System.out.println("Enter title to add:");
            Scanner scanner = new Scanner(System.in);
            String t = scanner.nextLine();
            System.out.println("Enter date to add:");
            String d = scanner.nextLine();
            dbManager.add(t, d);

            System.out.println("Enter title to edit:");
            t = scanner.nextLine();
            dbManager.edit(t);

            System.out.println("Enter title to remove:");
            t = scanner.nextLine();
            dbManager.remove(t);

            System.out.println("\n ALL Books:");
            List<Book> bookList = dbManager.allBooks();
            for(Book book: bookList){
                System.out.println(
                        " | " + book.getId() +
                        " \t| " + book.getTitle() +
                                "\t |" + book.getDate() + "\t |" );
            }

        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

}
