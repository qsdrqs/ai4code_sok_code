import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for the STUDENTS table.
 * We are assuming a SQL table called STUDENTS is available.
 * The table structure is assumed to be:
 * CREATE TABLE STUDENTS (
 *     ID INT PRIMARY KEY AUTO_INCREMENT,
 *     NAME VARCHAR(255),
 *     AGE INT
 * );
 */
public class StudentDAO {

    // --- Dependency Section (as per the request) ---
    // In a real application, these would be loaded from a configuration file.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    // -------------------------------------------------

    /**
     * Adds a new entry to the SQL table "STUDENTS".
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void add(String name, int age) {
        // Using a PreparedStatement is crucial to prevent SQL injection.
        // The '?' are placeholders for the values.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        System.out.println("Preparing to execute SQL: " + sql);

        // The 'try-with-resources' statement ensures that the connection and statement
        // are automatically closed, even if an error occurs.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the values to the placeholders
            pstmt.setString(1, name); // Binds the name to the first '?'
            pstmt.setInt(2, age);     // Binds the age to the second '?'

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, DELETE.
            // It returns the number of rows affected.
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " record inserted successfully.");
            }

        } catch (SQLException e) {
            // In Java, database errors throw a checked SQLException.
            // We catch it and print the error details.
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate the usage of the add function.
     */
    public static void main(String[] args) {
        // To run this, you would need to have a MySQL driver JAR in your classpath
        // and a running database with the specified credentials and table.
        StudentDAO studentDAO = new StudentDAO();
        
        // Example call
        studentDAO.add("John Doe", 22);
        studentDAO.add("Jane Smith", 21);
    }
}