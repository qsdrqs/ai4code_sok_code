import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    public static void addStudent(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

            // Prepare the SQL statement
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the statement
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("1 record inserted");
            }

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