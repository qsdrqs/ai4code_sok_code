import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {
    
    public static void insert(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/STUDENTS";
        String username = "yourusername";
        String password = "yourpassword";
        
        try {
            // Establish connection
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            
            // Prepare SQL statement with proper parameterized query
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            
            // Execute the query
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("1 record inserted");
            }
            
            // Close resources
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}