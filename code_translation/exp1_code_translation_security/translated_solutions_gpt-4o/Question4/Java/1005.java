import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    private static final String URL = "jdbc:mysql://your-database-url:3306/your-database-name";
    private static final String USER = "your-username";
    private static final String PASSWORD = "your-password";

    public static void addStudent(String name, int age) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare the SQL query
            String query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Student added successfully.");

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
        addStudent("John Doe", 20);
    }
}