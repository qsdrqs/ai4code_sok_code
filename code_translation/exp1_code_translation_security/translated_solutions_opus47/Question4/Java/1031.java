import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    public static void insert(String name, int age) {
        String url = "jdbc:mysql://localhost:3306/STUDENTS";
        String user = "yourusername";
        String password = "yourpassword";

        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println("Connected!");

            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("1 record inserted");
            }

        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}