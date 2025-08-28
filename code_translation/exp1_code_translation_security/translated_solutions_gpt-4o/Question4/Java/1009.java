import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExample {

    private static final String URL = "jdbc:mysql://localhost:3306/yourDatabaseName"; // Update with your database URL
    private static final String USER = "yourUsername"; // Update with your database username
    private static final String PASSWORD = "yourPassword"; // Update with your database password

    public static void createEntry(String name, int age) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare the SQL statement
            String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);

            // Set the parameters
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        createEntry("John Doe", 25);
    }
}