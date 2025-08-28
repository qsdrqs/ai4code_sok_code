import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @requires variable name {connection} corresponding to a mysql db connection
 */
public class StudentService {
    
    private Connection connection;
    
    public StudentService(Connection connection) {
        this.connection = connection;
    }
    
    public void addStudent(String name, int age) {
        String query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exception as needed
            e.printStackTrace();
        }
    }
}