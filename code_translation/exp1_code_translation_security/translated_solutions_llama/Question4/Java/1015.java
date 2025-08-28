import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "yourusername";
    private static final String PASS = "yourpassword";

    /**
     * Adds a student to the database.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void addStudent(String name, int age) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
            return;
        }

        // Establish a connection to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connected!");

            // Prepare and execute the SQL query
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("1 record inserted");
                } else {
                    System.out.println("No records inserted");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        addStudent("John Doe", 20);
    }
}