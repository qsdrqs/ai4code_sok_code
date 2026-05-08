import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager {
    private Connection connection;

    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + age + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("1 record inserted");
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}