package insertData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookPublisherHandler {

    public static void addBookPublisher(Connection conn, Scanner scanner, int entries) throws SQLException {
        int i =  0;
        while(i < entries){
            try{
                System.out.println("Introdu id-ul cartii: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Introdu id-ul editurii: ");
                int publisherId = scanner.nextInt();
                scanner.nextLine();

                String insertBookPublisher = "INSERT INTO Book_Publishers (book_id, publisher_id) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertBookPublisher)) {
                pstmt.setInt(1, bookId);
                pstmt.setInt(2, publisherId);
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
