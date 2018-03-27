import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class Library {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Library.class.getName());
    public static void main(String[] args) {

        Selection selection = new Selection();
        String str;

        do {
            System.out.println("Enter:");
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();


            try {
                selection.choice(str);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                log.error(e.getMessage(), "Exception: ", e);
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("\n To re-enter press 'Enter'");
            str = sc.nextLine();
        } while (!str.equals("exit"));
    }
}

