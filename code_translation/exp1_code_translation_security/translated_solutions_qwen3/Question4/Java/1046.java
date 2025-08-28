import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {

    // These values should be replaced with your actual database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    /**
     * Inserts a new student into the STUDENTS table.
     * WARNING: This method is vulnerable to SQL injection.
     * Use PreparedStatement for production code.
     */
    public static void insertInto(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (" + name + ", " + age + ")";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}