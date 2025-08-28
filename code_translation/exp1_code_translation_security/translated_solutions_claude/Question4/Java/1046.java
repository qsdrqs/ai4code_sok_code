import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    
    // Database configuration - replace with your actual database details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";
    
    public static void insertInto(String name, int age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Create connection to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            // Prepare SQL statement with placeholders to prevent SQL injection
            String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            
            // Set parameters
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            
            // Execute the query
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        insertInto("John Doe", 25);
    }
}