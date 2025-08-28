import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInserter {

    /**
     * Connects to a MySQL database and inserts a new student record.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void inserter(String name, int age) {
        // Database connection details
        String host = "localhost";
        String database = "mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // The connection URL for MySQL using JDBC
        // Includes parameters to avoid common SSL and timezone issues.
        String url = "jdbc:mysql://" + host + "/" + database + "?useSSL=false&serverTimezone=UTC";

        // The SQL query with placeholders (?) to prevent SQL injection
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Using try-with-resources to ensure the connection is automatically closed
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println("Connected to the database!");

            // Set the values for the placeholders
            pstmt.setString(1, name); // Corresponds to the first '?'
            pstmt.setInt(2, age);     // Corresponds to the second '?'

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("1 record inserted successfully.");
            }

        } catch (SQLException e) {
            // In Java, we catch SQLExceptions instead of using a callback with an error parameter.
            // Printing the stack trace is a common way to handle the error during development.
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate the usage of the inserter method.
     */
    public static void main(String[] args) {
        // Example of how to call the inserter method
        inserter("John Doe", 25);
    }
}