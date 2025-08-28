import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A utility class for database operations related to students.
 * This corresponds to the module exporting the addStudent function.
 */
public class StudentRepository {

    /**
     * Inserts a new student record into the STUDENTS table.
     *
     * Note: This function doesn't do any error checking on the types or sizes of `name`
     * and `age`. It relies on the database driver and schema to handle validation.
     *
     * @param conn The active database connection object. This corresponds to the `db` object.
     * @param name The student's name string.
     * @param age  The student's age integer.
     */
    public static void addStudent(Connection conn, String name, int age) {
        // The SQL query with placeholders (?) for parameters.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Using a try-with-resources statement ensures that the PreparedStatement
        // is automatically closed even if an exception occurs.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Bind the parameter values to the placeholders.
            // JDBC parameter indexes are 1-based.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the update. For INSERT, UPDATE, or DELETE, use executeUpdate().
            int affectedRows = pstmt.executeUpdate();

            // The original JS code logs the result of the operation.
            // We can do something similar here.
            if (affectedRows > 0) {
                System.out.println("Insertion successful for student: " + name);
            } else {
                System.out.println("Insertion failed, no rows were affected.");
            }

        } catch (SQLException e) {
            // This catch block is the equivalent of the `err` parameter in the JS callback.
            // It handles any errors that occur during the database operation.
            System.err.println("Error during insertion: " + e.getMessage());
            // In a real application, you would use a proper logging framework.
            // e.printStackTrace(); 
        }
    }
}