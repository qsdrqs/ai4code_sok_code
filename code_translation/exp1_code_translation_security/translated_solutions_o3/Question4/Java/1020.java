import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    public static void inserter(String name, int age) {
        // Connection information
        final String url      = "jdbc:mysql://localhost:3306/mydb";
        final String user     = "yourusername";
        final String password = "yourpassword";

        // SQL statement (use placeholders to avoid SQL-injection)
        final String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Fill in the placeholders
            stmt.setString(1, name);
            stmt.setInt(2, age);

            // Execute the statement
            stmt.executeUpdate();
            System.out.println("1 record inserted");

        } catch (SQLException e) {
            // Re-throw or log the exception as needed
            e.printStackTrace();
        }
    }
}