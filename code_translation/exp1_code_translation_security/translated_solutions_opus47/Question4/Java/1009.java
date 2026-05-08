import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    
    public static void createEntry(String name, int age) {
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
        
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}