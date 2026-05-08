import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://...";
    private static final String USER = "...";
    private static final String PASSWORD = "...";

    public static void addStudent(String name, int age) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement("INSERT INTO STUDENTS VALUES (?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}