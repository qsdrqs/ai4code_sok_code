import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for students.
 * For this code to work, you need a JDBC driver for your specific database
 * (e.g., mysql-connector-java for MySQL) in your project's classpath.
 */
public class StudentRepository {

    // Database connection details (replace with your actual credentials)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    /**
     * Inserts a new student entry into the STUDENTS table.
     * This method is equivalent to the JavaScript createEntry function.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void createEntry(String name, int age) {
        // The SQL query with placeholders (?) for parameters
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        // The try-with-resources statement automatically closes the connection and statement
        // This is the modern, recommended way to handle JDBC resources.
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            // Parameter indexes start at 1 in JDBC
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Entry created successfully for " + name);
            }

        } catch (SQLException e) {
            // In Java, database errors are handled with checked exceptions (SQLException)
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
            // You could re-throw this as a custom exception if needed
        }
    }

    /**
     * Example of how to use the createEntry method.
     */
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        repository.createEntry("Alice", 22);
        repository.createEntry("Bob", 25);
    }
}