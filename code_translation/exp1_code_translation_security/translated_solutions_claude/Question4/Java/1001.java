import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection db; // Assuming this is your database connection
    
    public void addSQLentry(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (var generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long lastID = generatedKeys.getLong(1);
                        System.out.println("User added with id: " + lastID);
                    }
                }
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}