import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class StudentDatabase {
    
    public static void addStudent(String name, int age) {
        String command = "INSERT INTO STUDENTS VALUES ('" + name + "','" + age + "');";
        Connection connection = null;
        Statement statement = null;
        
        try {
            // You'll need to replace this with your actual database URL, username, and password
            String url = "jdbc:your_database_url_here";
            String username = "your_username";
            String password = "your_password";
            
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(command);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}