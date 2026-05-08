import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager {
    
    private Connection connection;
    
    public StudentManager(Connection connection) {
        this.connection = connection;
    }
    
    public void add(String name, int age) {
        /* Add name, age to students table in MySQL */
        String query = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
            System.out.println("Added " + name + " to students table");
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}