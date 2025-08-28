import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    // --- Database Credentials (replace with your actual details) ---
    // It's best practice to load these from a configuration file rather than hardcoding them.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    /**
     * Inserts a new student record into the database.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void insertion(String name, int age) {
        // The SQL query with '?' placeholders to prevent SQL injection
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // The 'try-with-resources' statement ensures that the connection and statement
        // are automatically closed, even if an error occurs.
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Bind the values to the placeholders
            pstmt.setString(1, name); // Binds the 'name' to the first '?'
            pstmt.setInt(2, age);     // Binds the 'age' to the second '?'

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected + " row(s) inserted successfully.");

        } catch (SQLException e) {
            // Handle potential database errors
            System.err.println("Database insertion failed!");
            e.printStackTrace();
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.insertion("Alice", 22);
        dbManager.insertion("Bob", 25);
    }
}