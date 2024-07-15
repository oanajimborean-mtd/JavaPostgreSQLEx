package queryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookAuthorQueryManager {

    public static void queryBooksAndAuthors(Connection conn) {
        String query = "SELECT b.book_id, b.title, a.name FROM Books b INNER JOIN Authors a ON b.author_id = a.author_id";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String name = rs.getString("name");
                System.out.println("ID-ul cartii: " + bookId + ", Titlul cartii: " + title + ", Autorul: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
