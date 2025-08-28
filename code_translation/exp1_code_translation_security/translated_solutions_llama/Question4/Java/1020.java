import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void inserter(String name, int age) {
        // Database connection parameters
        String host = "localhost";
        String user = "yourusername";
        String password = "yourpassword";
        String database = "mydb";

        // JDBC URL
        String url = "jdbc:mysql://" + host + ":3306/" + database;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(url, user, password);

            // SQL query to insert a record into the STUDENTS table
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

            // Create a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set the parameters
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            int result = pstmt.executeUpdate();

            // Check if the record was inserted successfully
            if (result > 0) {
                System.out.println("1 record inserted");
            } else {
                System.out.println("Failed to insert record");
            }

            // Close the PreparedStatement and Connection
            pstmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        inserter("John Doe", 25);
    }
}