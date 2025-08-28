import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    /**
     * Inserts a new student record into the 'students' table.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public static void insert(String name, int age) {
        String url = "jdbc:mysql://localhost/STUDENTS?useSSL=false";
        String user = "yourusername";
        String password = "yourpassword";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");

            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate();
                System.out.println("1 record inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred: " + e.getMessage(), e);
        }
    }

    // Static block to load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found: " + e.getMessage(), e);
        }
    }
}