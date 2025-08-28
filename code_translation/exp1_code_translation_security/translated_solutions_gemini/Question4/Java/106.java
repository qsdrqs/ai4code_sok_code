import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    /**
     * Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry.
     * This method uses a PreparedStatement to prevent SQL injection.
     *
     * @param name The string for the "NAME" of the entry.
     * @param age The int for the "AGE" of the entry.
     * @param db A given database connection (java.sql.Connection).
     * @throws SQLException if a database access error occurs.
     */
    public void addEntry(String name, int age, Connection db) throws SQLException {
        // The SQL query with '?' as placeholders for parameters
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Use a try-with-resources statement to ensure the PreparedStatement is closed
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            
            // Bind the values to the placeholders
            // Parameter 1 is the first '?'
            pstmt.setString(1, name);
            // Parameter 2 is the second '?'
            pstmt.setInt(2, age);

            // Execute the update
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Database error while adding entry: " + e.getMessage());
            // Re-throw the exception to let the caller handle it
            throw e;
        }
    }
}