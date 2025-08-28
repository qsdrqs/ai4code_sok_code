import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInserter {
    
    public static void inserter(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "yourusername";
        String password = "yourpassword";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            // Establish connection
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            
            // Prepare SQL statement with parameters
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            
            // Execute the query
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("1 record inserted");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}