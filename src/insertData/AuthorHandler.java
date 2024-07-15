package insertData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthorHandler {

    public static void addAuthor(Connection conn, Scanner scanner, int entries) {
        int i = 0;
        String insertAuthor = "INSERT INTO Authors (author_id, name, date_of_birth) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertAuthor)) {
            while (i < entries) {
                try {
                    System.out.println("Introdu id-ul autorului: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();

                    String name = "";
                    boolean validName = false;
                    while (!validName) {
                        System.out.println("Introdu numele autorului: ");
                        name = scanner.nextLine();
                        if (!isValidName(name)) {
                            System.err.println("Error: Numele autorului trebuie să contina doar litere.");
                        } else {
                            validName = true;
                        }
                    }

                    boolean validDate = false;
                    String birthDate = "";

                    while (!validDate) {
                        System.out.println("Introdu data nasterii a autorului in format yyyy-mm-dd: ");
                        birthDate = scanner.nextLine();
                        try {
                            java.sql.Date.valueOf(birthDate);
                            validDate = true;
                        } catch (IllegalArgumentException iae) {
                            System.err.println("Error: Te rog introdu data in formatul yyyy-mm-dd");
                        }
                    }

                    pstmt.setInt(1, authorId);
                    pstmt.setString(2, name);
                    pstmt.setDate(3, java.sql.Date.valueOf(birthDate));
                    pstmt.executeUpdate();
                    i++;

                } catch (InputMismatchException e) {
                    System.err.println("Error: Formatul id-ului nu este corespunzator. Te rog introdu un id valid.");
                    scanner.nextLine();
                } catch (SQLException sqlEx) {
                    System.err.println("Database error: " + sqlEx.getMessage());
                }
            }
        } catch(SQLException e){
            System.err.println("Eroare la prepared statement: " + e.getMessage());
            }
        }


    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }
}
