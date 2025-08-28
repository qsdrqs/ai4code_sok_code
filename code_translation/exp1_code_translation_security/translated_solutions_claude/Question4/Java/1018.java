import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseManager {
    
    public static void add(String name, int age, String table) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // Load database configuration
            Properties config = new Properties();
            config.load(new FileInputStream("config.properties"));
            
            // Create database connection
            String url = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");
            
            connection = DriverManager.getConnection(url, username, password);
            
            // Prepare SQL statement
            String sql = "INSERT INTO todos(title, completed) VALUES (?, ?)";
            stmt = connection.prepareStatement(sql);
            
            // Set parameters
            stmt.setString(1, name);
            stmt.setInt(2, age);
            
            // Execute query
            int affectedRows = stmt.executeUpdate();
            
            // Print results
            System.out.println("Row inserted: " + affectedRows);
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
            return;
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}