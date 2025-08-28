import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Assuming 'connection' is a valid MySQL database connection
    private Connection connection;

    public StudentDatabase(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(String name, int age) {
        String query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }
}