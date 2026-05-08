import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    
    // Database configuration (equivalent to the 'config' object in JavaScript)
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";
    
    public static void insertStudent(String name, int age) {
        // insert statement
        String sql = "INSERT INTO STUDENTS(title, completed) " +
                     "VALUES('Learn how to insert a new row', true)";
        
        // try-with-resources automatically handles connection.end() equivalent
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // execute the insert statement
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}