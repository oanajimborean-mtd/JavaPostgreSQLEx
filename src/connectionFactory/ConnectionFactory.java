package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost/biblioteca";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
