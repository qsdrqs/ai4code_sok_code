import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistration {
    
    public static boolean registerStudent(String name, int age) {
        // Create student object (using local variables since Java doesn't have object literals like JS)
        String studentName = name;
        int studentAge = age;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Create connection (you'll need to replace with your actual database URL, username, password)
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", 
                "username", 
                "password"
            );
            
            // Use PreparedStatement to avoid SQL injection (better than string concatenation)
            String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentName);
            preparedStatement.setInt(2, studentAge);
            
            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Clean up resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}