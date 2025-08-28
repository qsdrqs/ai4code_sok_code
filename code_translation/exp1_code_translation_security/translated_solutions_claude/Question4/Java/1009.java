import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection con;
    
    // Constructor to initialize connection (you'll need to set your database URL, username, password)
    public DatabaseManager(String url, String username, String password) throws SQLException {
        this.con = DriverManager.getConnection(url, username, password);
    }
    
    public void createEntry(String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e; // Re-throw the exception (equivalent to "throw err" in JavaScript)
        }
    }
    
    // Method to close the connection when done
    public void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}