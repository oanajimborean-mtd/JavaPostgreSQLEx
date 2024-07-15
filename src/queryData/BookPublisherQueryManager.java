package queryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookPublisherQueryManager {

    public static void queryBooksAndPublishers(Connection conn) {
        String query = "SELECT p.name, b.title FROM Book_Publishers q INNER JOIN Publishers p ON q.publisher_id = p.publisher_id " +
                "INNER JOIN Books b ON q.book_id = b.book_id";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String title = rs.getString("title");
                System.out.println("Cartea " + title + " a fost publicata de editura: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
