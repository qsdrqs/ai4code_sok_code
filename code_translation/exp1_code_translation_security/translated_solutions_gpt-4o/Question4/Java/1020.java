import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInserter {

    public static void inserter(String name, int age) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // SQL query to insert data
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Establishing connection and executing the query
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Setting the parameters for the query
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Executing the query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("1 record inserted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        inserter("John Doe", 25);
    }
}