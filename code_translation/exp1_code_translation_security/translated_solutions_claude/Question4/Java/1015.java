import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {
    
    public static void addStudent(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "yourusername";
        String password = "yourpassword";
        
        Connection con = null;
        Statement stmt = null;
        
        try {
            // Establish connection
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            
            // Create and execute SQL statement
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', '" + age + "')";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            
            System.out.println("1 record inserted");
            
        } catch (SQLException err) {
            throw new RuntimeException(err);
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}