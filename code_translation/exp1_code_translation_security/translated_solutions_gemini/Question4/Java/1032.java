import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// A mock/dummy connection class for demonstration purposes if you don't have a real DB connection.
import java.sql.DriverManager;

/**
 * A class that securely handles database operations for students.
 */
public class StudentRepository {

    /**
     * Securely adds a student to the database using a PreparedStatement.
     *
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     * @throws SQLException if a database access error occurs.
     */
    public static void addStudentSecurely(Connection conn, String name, int age) throws SQLException {
        // The SQL query uses placeholders (?) instead of concatenating user input.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // The try-with-resources statement ensures the PreparedStatement is closed automatically.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Bind the values to the placeholders. This is the safe part.
            // The driver ensures the values are treated as data, not as part of the SQL command.
            pstmt.setString(1, name); // Binds the name to the first '?'
            pstmt.setInt(2, age);     // Binds the age to the second '?'

            System.out.println("Executing PreparedStatement: " + pstmt);

            // In a real application, you would execute the statement to update the database.
            // int rowsAffected = pstmt.executeUpdate();
            // System.out.println(rowsAffected + " row(s) inserted.");
        }
    }

    /**
     * The main method to demonstrate the secure approach.
     */
    public static void main(String[] args) {
        // To run this example, you would need a JDBC driver and a database.
        // We'll use an in-memory H2 database for a self-contained demonstration.
        // In a real app, you'd get this connection from a connection pool.
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb")) {
            System.out.println("Successfully connected to in-memory database.");
            
            // Create a dummy table for the example to work
            conn.createStatement().execute("CREATE TABLE STUDENTS (NAME VARCHAR(255), AGE INT)");

            // --- Example 1: A normal, safe insertion ---
            System.out.println("\n--- Running safe example ---");
            addStudentSecurely(conn, "Jane Doe", 30);

            // --- Example 2: A malicious input attempt ---
            // This would be dangerous in the first version, but is safe here.
            System.out.println("\n--- Running malicious input example ---");
            String maliciousName = "Robert'); DROP TABLE STUDENTS; --";
            addStudentSecurely(conn, maliciousName, 99);
            
            System.out.println("\nNotice how the malicious input was treated as a literal string and did not harm the database.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            // In a real application, you would log this error.
        }
    }
}