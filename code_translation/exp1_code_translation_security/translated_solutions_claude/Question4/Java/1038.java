import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperations {
    
    public void insertion(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";
        
        try {
            // Create connection
            Connection con = DriverManager.getConnection(url, username, password);
            
            // SQL query with placeholders for parameters
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            
            // Create prepared statement
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            
            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully!");
            }
            
            // Close resources
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}