import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    public static void addStudent(String name, int age) {
        String command = "INSERT INTO STUDENTS VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:your_connection_string_here");
             PreparedStatement statement = connection.prepareStatement(command)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}