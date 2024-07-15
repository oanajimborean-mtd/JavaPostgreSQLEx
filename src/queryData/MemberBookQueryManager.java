package queryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberBookQueryManager {

    public static void queryMembersAndBooks(Connection conn) {
        String query = "SELECT m.name, STRING_AGG(b.title,', ') AS borrowedBooks FROM Members m LEFT JOIN Loans l ON l.member_id = m.member_id " +
                "LEFT JOIN Books b ON l.book_id = b.book_id " +
                "GROUP BY m.name";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String borrowedBooks = rs.getString("borrowedBooks");

                if(borrowedBooks == null){
                    System.out.println("Members that did not borrow books: " + name);
                    continue;
                }
                System.out.println("Membrul " + name + " a imprumutat: " + borrowedBooks);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
