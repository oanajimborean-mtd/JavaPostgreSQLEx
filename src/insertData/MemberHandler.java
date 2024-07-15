package insertData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberHandler {

    public static void addMember(Connection conn, Scanner scanner, int entries) throws SQLException {
        int i = 0;
        while(i < entries) {
            try {
                    System.out.println("Introdu id-ul membrului: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();

                    String name = "";
                    boolean validName = false;
                    while (!validName) {
                        System.out.println("Introdu numele membrului: ");
                        name = scanner.nextLine();
                        if (!isValidName(name)) {
                            System.err.println("Error: Numele membrului trebuie să contina doar litere.");
                        } else {
                        validName = true;
                        }
                    }

                    boolean validDate = false;
                    String dateSignup = "";

                    while (!validDate) {
                        System.out.println("Introdu data nasterii a autorului in format yyyy-mm-dd: ");
                        dateSignup = scanner.nextLine();
                        try {
                            java.sql.Date.valueOf(dateSignup);
                            validDate = true;
                        } catch (IllegalArgumentException iae) {
                        System.err.println("Error: Te rog introdu data in formatul yyyy-mm-dd");
                        }
                    }

                String insertMember = "INSERT INTO Members (member_id, name, date_signup) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertMember)) {
                    pstmt.setInt(1, memberId);
                    pstmt.setString(2, name);
                    pstmt.setDate(3, java.sql.Date.valueOf(dateSignup));
                    pstmt.executeUpdate();
                }
                    i++;

            } catch(InputMismatchException e){
                System.err.println("Error: Formatul id-ului nu este corespunzator. Te rog introdu un id valid.");
                scanner.nextLine();
            } catch(SQLException sqlEx){
                System.err.println("Database error: " + sqlEx.getMessage());
            }
        }

    }
    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }
}
