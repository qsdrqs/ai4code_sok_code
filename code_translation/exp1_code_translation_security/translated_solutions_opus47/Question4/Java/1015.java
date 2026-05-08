import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    public static void addStudent(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate();
                System.out.println("1 record inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}