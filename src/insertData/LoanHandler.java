package insertData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoanHandler {
    public static void addLoan(Connection conn, Scanner scanner, int entries) throws SQLException {
        int i = 0;
        while (i < entries) {
            try {
                    System.out.println("Introdu id-ul imprumutului: ");
                    int loanId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu id-ul cartii: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu id-ul membrului: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu data cand s-a efectuat imprumutul in format yyyy-mm-dd: ");
                    String dateLoan = scanner.nextLine();

                    String insertLoan = "INSERT INTO Loans (loan_id, book_id, member_id, date_loan) VALUES (?, ?, ?, ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(insertLoan)) {
                        pstmt.setInt(1, loanId);
                        pstmt.setInt(2, bookId);
                        pstmt.setInt(3, memberId);
                        pstmt.setDate(4, java.sql.Date.valueOf(dateLoan));
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
}
