import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class demonstrates how to insert data into a MySQL database using JDBC.
 *
 * --- Dependency Requirement ---
 * To run this code, you need the MySQL JDBC driver. If you are using a build
 * tool like Maven, add the following dependency to your pom.xml:
 *
 * <dependency>
 *     <groupId>mysql</groupId>
 *     <artifactId>mysql-connector-java</artifactId>
 *     <version>8.0.33</version> <!-- Or the latest version -->
 * </dependency>
 *
 */
public class DatabaseInserter {

    // --- Database Configuration ---
    // Replace with your actual database connection details.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    /**
     * Inserts a new student record into the STUDENTS table.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void insertInto(String name, int age) {
        // The SQL query with placeholders (?) for parameters.
        // Using placeholders is crucial to prevent SQL injection attacks.
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

        // Using try-with-resources to ensure the connection and statement are
        // automatically closed, even if an error occurs.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the values to the placeholders.
            // Parameter index starts from 1.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, DELETE.
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected + " row(s) inserted successfully.");

        } catch (SQLException e) {
            // Handle potential database errors.
            System.err.println("Database insertion failed!");
            e.printStackTrace();
        }
    }

    /**
     * Main method to run a test insertion.
     */
    public static void main(String[] args) {
        // Example usage of the insertInto function.
        insertInto("Alice", 22);
        insertInto("Bob", 25);
    }
}