package insertData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PublisherHandler {
    public static void addPublisher(Connection conn, Scanner scanner, int entries) throws SQLException {
        int i = 0;
        while (i < entries) {
            try {
                    System.out.println("Introdu id-ul editurii: ");
                    int publisherId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu numele editurii: ");
                    String name = scanner.nextLine();


                    String insertPublisher = "INSERT INTO Publishers (publisher_id, name) VALUES (?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertPublisher)) {
                        pstmt.setInt(1, publisherId);
                        pstmt.setString(2, name);
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
