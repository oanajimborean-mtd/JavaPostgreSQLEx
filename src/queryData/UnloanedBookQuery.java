package queryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnloanedBookQuery {
    public static void queryUnloanedBooks(Connection conn) {
        String query = "select b.title from books b where b.book_id not in (select book_id from loans)";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");

                System.out.println("Cartile care nu au fost imprumutate: " + title);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }

}
