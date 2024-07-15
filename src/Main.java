import connectionFactory.ConnectionFactory;
import option.Options;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            Options.processOptions(conn, scanner);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
