import java.sql.SQLException;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        Menu menu = new Menu();
        String str;

        do {
            System.out.println("Enter:");

            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();

            try {
                menu.choice(str);

            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("\n To re-enter press 'Enter'");
            str = sc.nextLine();
        } while (!str.equals("exit"));
    }
}

