import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class demonstrates how to insert data into a database using JDBC.
 * It is a Java equivalent of the provided JavaScript function.
 */
public class DatabaseManager {

    // --- Database Configuration ---
    // In a real application, load these from a properties file.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    /**
     * Inserts a new record into a specified table.
     *
     * Note: The original JavaScript code had a logical mismatch. It accepted 'name' and 'age'
     * as parameters but tried to insert them into 'title' and 'completed' columns in a 'todos' table.
     * This Java version corrects this logic by inserting 'name' and 'age' into corresponding columns.
     * The table name is also used dynamically, as suggested by the function signature.
     *
     * @param name The name to insert.
     * @param age The age to insert.
     * @param table The name of the table to insert the record into.
     */
    public static void add(String name, int age, String table) {
        // The SQL statement with placeholders (?) for parameters.
        // IMPORTANT: Table names cannot be parameterized with PreparedStatement to prevent SQL injection.
        // Here, we concatenate it. Ensure the 'table' variable comes from a trusted source.
        String sql = "INSERT INTO " + table + "(name, age) VALUES (?, ?)";

        // Using try-with-resources to ensure the connection and statement are automatically closed.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the values to the placeholders in the PreparedStatement
            pstmt.setString(1, name); // Sets the first '?' to the value of 'name'
            pstmt.setInt(2, age);     // Sets the second '?' to the value of 'age'

            // Execute the query and get the number of affected rows
            int affectedRows = pstmt.executeUpdate();

            System.out.println("Rows inserted: " + affectedRows);

        } catch (SQLException e) {
            // Handle potential SQL errors
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Example of how to call the add method.
     */
    public static void main(String[] args) {
        // Example usage: Add a user named "John Doe", age 30, to the "users" table.
        // Make sure a table named "users" with columns "name" (VARCHAR) and "age" (INT) exists.
        add("John Doe", 30, "users");
        add("Jane Smith", 25, "users");
    }
}