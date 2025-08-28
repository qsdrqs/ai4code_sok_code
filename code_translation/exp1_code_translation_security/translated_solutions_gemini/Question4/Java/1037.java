import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentInserterImproved {

    // --- Database Configuration ---
    // Replace with your actual database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    /**
     * Inserts a new student into the database using the provided name and age.
     * This version uses a PreparedStatement to prevent SQL injection.
     *
     * @param name The student's name to insert.
     * @param age The student's age to insert.
     */
    public static void insertStudent(String name, int age) {
        // The SQL statement uses '?' as placeholders for parameters.
        // Note: This assumes a table named STUDENTS with columns 'name' and 'age'.
        String sql = "INSERT INTO STUDENTS(name, age) VALUES(?, ?)";

        // The try-with-resources statement ensures that resources (Connection, PreparedStatement) are closed.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the parameter values to the '?' placeholders
            pstmt.setString(1, name); // Sets the first '?' to the value of 'name'
            pstmt.setInt(2, age);     // Sets the second '?' to the value of 'age'

            // Execute the insert statement and get the number of affected rows
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected + " row(s) inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Call the method with actual data to be inserted.
        insertStudent("Jane Doe", 22);
        insertStudent("John Smith", 25);
        
        // This call mimics the original 'insertStudent("XXXXX", 0)'
        insertStudent("XXXXX", 0);
    }
}