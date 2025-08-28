import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    // Function to add a student to the STUDENTS table
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        System.out.println(sql);
        return sql;
    }

    public static void main(String[] args) {
        // Database connection parameters
        String host = "localhost";
        String user = "yourusername";
        String password = "yourpassword";
        String database = "yourdatabase"; // Specify your database name

        // JDBC URL
        String url = "jdbc:mysql://" + host + ":3306/" + database;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

            // Prepare the SQL statement
            String sql = addStudent("XXXXX", 0);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "XXXXX");
            pstmt.setInt(2, 0);

            // Execute the SQL statement
            int result = pstmt.executeUpdate();
            System.out.println("Result: " + result);

            // Close the connection
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error interacting with database: " + e.getMessage());
        }
    }
}