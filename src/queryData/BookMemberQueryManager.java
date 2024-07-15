package queryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookMemberQueryManager {

    public static void queryBooksAndTheirMember(Connection conn) {
        String query = "SELECT b.title, STRING_AGG (m.name, ', ') AS borrowers FROM Books b LEFT JOIN Loans l ON l.book_id = b.book_id " +
                "LEFT JOIN Members m ON l.member_id = m.member_id GROUP BY b.title";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String borrowers = rs.getString("borrowers");
                String title = rs.getString("title");
                System.out.println("Cartea " + title + " a fost imprumutata de: " + borrowers);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
