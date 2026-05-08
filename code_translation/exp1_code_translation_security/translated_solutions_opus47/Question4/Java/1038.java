import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void insertion(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Using try-with-resources to auto-close Connection and PreparedStatement
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Set parameters safely (prevents SQL injection)
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the insert statement
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");

        } catch (SQLException err) {
            // Equivalent to "throw err" in the original JS
            throw new RuntimeException("Database error: " + err.getMessage(), err);
        }
    }

    public static void main(String[] args) {
        insertion("John Doe", 20);
    }
}