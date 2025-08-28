import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    /**
     * Inserts a student record into the STUDENTS table.
     *
     * @param name Student’s name
     * @param age  Student’s age
     * @throws SQLException if a database access error occurs
     */
    public void addStudent(String name, int age) throws SQLException {
        // Parameterized statement to avoid SQL-injection
        String sql = "INSERT INTO STUDENTS VALUES (?, ?)";

        // Substitute your own JDBC URL, user name, and password if needed.
        String url      = "jdbc:your_database_url";
        String user     = "your_user";
        String password = "your_password";

        // try-with-resources closes Connection and PreparedStatement automatically
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        }
    }
}