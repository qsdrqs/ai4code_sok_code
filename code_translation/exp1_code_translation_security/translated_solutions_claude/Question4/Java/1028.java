import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public void addStudent(String name, int age) {
    String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        
        int result = preparedStatement.executeUpdate();
        System.out.println("1 record inserted");
    } catch (SQLException err) {
        throw new RuntimeException(err);
    }
}