import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentQueryGenerator {
    public static void addStudent(Connection connection, String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        // Assume a Connection object is obtained
        // Connection connection = DriverManager.getConnection("url", "user", "password");
        // addStudent(connection, "XXXXX", 0);
    }
}