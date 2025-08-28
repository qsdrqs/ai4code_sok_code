import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class contains a method to add a student to the database.
 * It assumes a Connection object is provided.
 */
public class StudentRepository {

    /**
     * Inserts a new student record into the STUDENTS table.
     *
     * @param connection The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void addStudent(Connection connection, String name, int age) {
        // The SQL query with '?' placeholders for parameters
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        // Using a try-with-resources statement to ensure the PreparedStatement is closed automatically
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Bind the values to the placeholders
            // Parameter 1 is the first '?'
            pstmt.setString(1, name);
            // Parameter 2 is the second '?'
            pstmt.setInt(2, age);

            // Execute the update and get the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            // Log the result to the console
            System.out.println(rowsAffected + " record(s) inserted.");

        } catch (SQLException e) {
            // Handle potential SQL errors
            System.err.println("Database error: " + e.getMessage());
            // In a real application, you would likely log this error or throw a custom exception
            // e.printStackTrace();
        }
    }
}