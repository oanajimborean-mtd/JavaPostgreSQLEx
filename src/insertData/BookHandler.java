package insertData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookHandler {

    public static void addBook(Connection conn, Scanner scanner, int entries) {
        int i = 0;
        while (i < entries) {
            try {
                    System.out.println("Introdu id-ul cartii: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu titlul cartii: ");
                    String title = scanner.nextLine();

                    System.out.println("Introdu id-ul autorului: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();

                    String insertBook = "INSERT INTO Books (book_id, title, author_id) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertBook)) {
                        pstmt.setInt(1, bookId);
                        pstmt.setString(2, title);
                        pstmt.setInt(3, authorId);
                        pstmt.executeUpdate();
                    }
                    i++;
            } catch (InputMismatchException e) {
                System.err.println("Error: Formatul id-ului nu este corespunzator. Te rog introdu un id valid.");
                scanner.nextLine();
            } catch (SQLException sqlEx) {
                System.err.println("Database error: " + sqlEx.getMessage());
            }
        }
    }
}
