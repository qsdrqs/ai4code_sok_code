import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsertion {

    public static void insertion(String name, int age) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/yourDatabaseName"; // Replace with your database URL
        String user = "yourUsername"; // Replace with your database username
        String password = "yourPassword"; // Replace with your database password

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(url, user, password);

            // SQL query
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

            // Create a PreparedStatement
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully");

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
        // Example usage
        insertion("John Doe", 25);
    }
}