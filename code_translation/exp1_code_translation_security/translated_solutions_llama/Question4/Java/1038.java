import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void insertion(String name, int age) {
        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/your_database"; // replace with your database URL
        String username = "your_username"; // replace with your database username
        String password = "your_password"; // replace with your database password

        // SQL query
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(dbUrl, username, password);

            // Create a prepared statement
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            pstmt.executeUpdate();

            // Close the prepared statement and connection
            pstmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        insertion("John Doe", 25);
    }
}