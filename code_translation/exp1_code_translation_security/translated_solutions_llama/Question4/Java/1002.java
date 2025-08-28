import com.microsoft.sqlserver.jdbc.ISQLServerPreparedStatement;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    /**
     * Inserts a new user into the STUDENTS table.
     *
     * @param conn The active database connection.
     * @param name The name of the user.
     * @param age  The age of the user.
     */
    public static void insertUser(Connection conn, String name, int age) {
        String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Assume active connection
        // Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=yourDB", "yourUser", "yourPassword");

        // Example usage
        // insertUser(conn, "John Doe", 30);
    }
}