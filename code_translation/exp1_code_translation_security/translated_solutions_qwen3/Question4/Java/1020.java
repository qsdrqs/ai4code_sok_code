import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentInserter {

    public static void inserter(String name, int age) {
        try {
            // Load the MySQL JDBC driver (optional in JDBC 4.0+ if in classpath)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }

        // Database connection details
        String url = "jdbc:mysql://localhost/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // Use try-with-resources to automatically close resources
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")) {

            System.out.println("Connected!");

            // Set the parameters in the prepared statement
            stmt.setString(1, name);
            stmt.setInt(2, age);

            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            System.out.println("1 record inserted");

        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
}