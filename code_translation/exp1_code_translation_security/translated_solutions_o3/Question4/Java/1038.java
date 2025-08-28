import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    /*  Connection information is normally provided by your environment
        (properties file, environment variables, connection-pool, etc.).
        Placeholders are used here for clarity; replace them with real values. */
    private static final String URL      = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER     = "your_user";
    private static final String PASSWORD = "your_password";

    /**
     * Inserts a student record into the STUDENTS table.
     *
     * @param name the student's name
     * @param age  the student's age
     */
    public static void insertion(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        /*  try-with-resources automatically closes the Connection and
            PreparedStatement even if an exception is thrown.            */
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();

        } catch (SQLException e) {
            // Re-throw, log, or handle the exception as your project requires.
            e.printStackTrace();
        }
    }
}