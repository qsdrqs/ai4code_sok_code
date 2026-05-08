import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example {
    // assume active conn
    public static void insertUser(Connection conn, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
        try (PreparedStatement request = conn.prepareStatement(sql)) {
            request.setNString(1, name);
            request.setInt(2, age);
            request.executeUpdate();
        } catch (SQLException err) {
            System.out.println(err);
        }
    }
}