import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddExample {

    /**
     * Inserts a new record into the 'todos' table.
     * 
     * @param name  The name/title to insert.
     * @param age   The age/completed status to insert.
     * @param table The table name (unused in this implementation).
     */
    public static void add(String name, int age, String table) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details (hardcoded as per the note)
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "password";

            // Establish connection using try-with-resources
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                // SQL statement with placeholders
                String sql = "INSERT INTO todos (title, completed) VALUES (?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    // Set the parameters
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);

                    // Execute the insert
                    int affectedRows = pstmt.executeUpdate();

                    // Log the result
                    System.out.println("Row inserted: " + affectedRows);
                }
            }
        } catch (ClassNotFoundException e) {
            // Handle driver not found
            System.err.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            // Handle SQL errors
            System.err.println("SQL error: " + e.getMessage());
        }
    }
}