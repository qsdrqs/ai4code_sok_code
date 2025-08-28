import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class demonstrates how to connect to a MySQL database and insert data
 * using JDBC.
 *
 * NOTE: You need to have a MySQL JDBC driver (e.g., mysql-connector-j.jar)
 * in your project's classpath for this code to run.
 */
public class DatabaseInsert {

    // Database connection details
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306"; // Default MySQL port
    private static final String DB_NAME = "mydb";
    private static final String DB_USER = "yourusername";
    private static final String DB_PASSWORD = "yourpassword";

    // The connection URL for JDBC
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    public static void main(String[] args) {
        insert();
    }

    public static void insert() {
        // The SQL query with placeholders (?) for secure data insertion
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        // Using try-with-resources to automatically handle user input closing
        try (Scanner scanner = new Scanner(System.in)) {
            // Read input from the user
            System.out.print("enter a name: ");
            String name = scanner.nextLine();

            System.out.print("enter an age: ");
            int age = scanner.nextInt();

            // Using try-with-resources to ensure the database connection and statement
            // are automatically closed, even if an error occurs.
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql)) {

                System.out.println("Connected!");

                // Bind the user-provided values to the SQL query placeholders
                pstmt.setString(1, name); // Sets the first '?' to the name
                pstmt.setInt(2, age);     // Sets the second '?' to the age

                // Execute the update and get the number of rows affected
                int affectedRows = pstmt.executeUpdate();

                System.out.println("Number of records inserted: " + affectedRows);

            } catch (SQLException e) {
                // Handle any potential SQL errors (e.g., connection failed, query syntax error)
                e.printStackTrace();
            }
        }
    }
}