import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    public static void insertion(String name, int age) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/test"; // Replace with your DB name
        String user = "root";                            // Replace with your DB username
        String password = "";                            // Replace with your DB password

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                // Define the SQL query with placeholders
                String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

                // Create a prepared statement
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // Set the parameters
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);

                    // Execute the insert
                    pstmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any errors
            e.printStackTrace();
        }
    }
}