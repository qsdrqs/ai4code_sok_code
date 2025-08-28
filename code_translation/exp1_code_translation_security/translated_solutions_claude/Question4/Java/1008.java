import java.sql.*;
import javax.swing.JOptionPane;

public class StudentDatabase {
    private static final String DB_URL = "jdbc:sqlite:mydb.db";
    private static final String DB_NAME = "mydb";
    private static final String DB_VERSION = "1.0";
    private static final String DB_DESCRIPTION = "Test DB";
    private static final int DB_SIZE = 2 * 1024 * 1024; // 2MB
    
    private Connection connection;
    
    // Constructor to initialize database connection
    public StudentDatabase() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Java method to create SQL table "students" with 'name' and 'age'
     */
    public void createTable() {
        try {
            // Begin transaction
            connection.setAutoCommit(false);
            
            String sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Execute the SQL statement
            statement.executeUpdate();
            
            // Commit transaction
            connection.commit();
            
            // Success callback equivalent
            JOptionPane.showMessageDialog(null, "Table created successfully");
            
            statement.close();
            
        } catch (SQLException e) {
            try {
                // Rollback transaction on error
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            
            // Error callback equivalent
            JOptionPane.showMessageDialog(null, "Error occurred while creating the table.");
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Java method to insert string name and an int age to 'students'
     */
    public void insertStudent(String name, int age) {
        try {
            // Begin transaction
            connection.setAutoCommit(false);
            
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Set parameters
            statement.setString(1, name);
            statement.setInt(2, age);
            
            // Execute the SQL statement
            statement.executeUpdate();
            
            // Commit transaction
            connection.commit();
            
            statement.close();
            
        } catch (SQLException e) {
            try {
                // Rollback transaction on error
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Method to close database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        
        // Create table
        db.createTable();
        
        // Insert some students
        db.insertStudent("John Doe", 20);
        db.insertStudent("Jane Smith", 22);
        
        // Close connection when done
        db.closeConnection();
    }
}