import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {

    public static void addStudent(String name, String age) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }

        // Database connection details
        String url = "jdbc:mysql://localhost/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // Establish connection and execute SQL
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement()) {

            System.out.println("Connected!");

            // Construct the SQL query using string concatenation
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', '" + age + "')";

            // Execute the insert
            stmt.executeUpdate(sql);

            System.out.println("1 record inserted");

        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }
}