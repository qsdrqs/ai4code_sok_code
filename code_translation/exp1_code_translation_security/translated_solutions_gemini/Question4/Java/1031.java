import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for students.
 */
public class StudentDAO {

    // --- Database Connection Details ---
    // It's good practice to define these as constants.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENTS";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";

    /**
     * Inserts a new student record into the database.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void insert(String name, int age) {
        // The SQL query with '?' placeholders for parameters.
        // This is a PreparedStatement, which prevents SQL injection attacks.
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        // The try-with-resources statement ensures that each resource is closed
        // at the end of the statement, which is crucial for preventing resource leaks.
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println("Connected to the database successfully!");

            // Bind the parameter values to the '?' placeholders
            pstmt.setString(1, name); // Sets the first '?' to the student's name
            pstmt.setInt(2, age);     // Sets the second '?' to the student's age

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, or DELETE.
            // It returns the number of rows affected.
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " record inserted successfully.");
            }

        } catch (SQLException e) {
            // In Java, errors are handled with try-catch blocks instead of callbacks.
            // A SQLException is thrown if there is a connection or query error.
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A main method to demonstrate how to use the insert method.
     */
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        // Example usage:
        dao.insert("Alice", 22);
        dao.insert("Bob", 25);
    }
}