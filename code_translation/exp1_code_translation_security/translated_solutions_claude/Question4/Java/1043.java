import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {
    
    private Connection connection;
    
    // Constructor to initialize database connection
    public StudentDatabase(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }
    
    /**
     * adds a new entry to SQL table "STUDENTS"
     */
    public void add(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        System.out.println(sql);
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            
            int result = preparedStatement.executeUpdate();
            
            if (result > 0) {
                System.out.println("1 record inserted");
            }
            
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
    
    // Method to close the connection
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}