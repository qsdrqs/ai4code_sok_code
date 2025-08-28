import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    // Database connection properties
    private static final String DB_HOST = "localhost";
    private static final String DB_USER = "yourusername";
    private static final String DB_PASSWORD = "yourpassword";
    private static final String DB_NAME = "STUDENTS";

    // SQL query to insert a record into the students table
    private static final String INSERT_QUERY = "INSERT INTO students (name, age) VALUES (?, ?)";

    /**
     * Inserts a record into the students table.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public static void insert(String name, int age) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
            return;
        }

        // Establish a connection to the database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + DB_HOST + "/" + DB_NAME,
                DB_USER,
                DB_PASSWORD
        )) {
            System.out.println("Connected!");

            // Prepare and execute the INSERT query
            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("1 record inserted");
                } else {
                    System.out.println("Error inserting record");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        insert("John Doe", 20);
    }
}