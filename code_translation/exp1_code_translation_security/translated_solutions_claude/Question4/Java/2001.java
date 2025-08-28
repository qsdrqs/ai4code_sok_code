import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public void add(String name, int age) {
    /* Add name, age to students table in MySQL */
    String query = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Added " + name + " to students table");
        }
    } catch (SQLException err) {
        throw new RuntimeException(err);
    }
}