package insertData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthorHandler {

    public static void addAuthor(Connection conn, Scanner scanner, int entries) {
        int i = 0;
        while(i < entries) {
            try {
                    System.out.println("Introdu id-ul autorului: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu numele autorului: ");
                    String name = scanner.nextLine();

                    if (!isValidName(name)) {
                    System.err.println("Error: Numele autorului trebuie sa conțina doar litere.");
                    continue;
                    }

                    System.out.println("Introdu data nasterii a autorului in format yyyy-mm-dd: ");
                    String birthDate = scanner.nextLine();

                    String insertAuthor = "INSERT INTO Authors (author_id, name, date_of_birth) VALUES (?, ?, ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(insertAuthor)) {
                        pstmt.setInt(1, authorId);
                        pstmt.setString(2, name);
                        pstmt.setDate(3, java.sql.Date.valueOf(birthDate));
                        pstmt.executeUpdate();
                    }
                    i++;
            } catch (InputMismatchException e) {
                System.err.println("Error: Formatul id-ului nu este corespunzator. Te rog introdu un id valid.");
                scanner.nextLine();
            } catch (IllegalArgumentException iae) {
                System.err.println("Error: Te rog introdu data in formatul yyyy-mm-dd");
            } catch (SQLException sqlEx) {
                System.err.println("Database error: " + sqlEx.getMessage());
            }
        }
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }
}
