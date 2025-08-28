import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager {

    private Connection connection;

    public StudentManager(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("1 record inserted");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert student", e);
        }
    }
}