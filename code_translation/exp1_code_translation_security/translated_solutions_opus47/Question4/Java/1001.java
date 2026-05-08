import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public void addSQLentry(String name, int age) {
    String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    try (PreparedStatement pstmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();

        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long lastID = generatedKeys.getLong(1);
                System.out.println("User added with id: " + lastID);
            }
        }
    } catch (SQLException err) {
        System.out.println(err);
    }
}