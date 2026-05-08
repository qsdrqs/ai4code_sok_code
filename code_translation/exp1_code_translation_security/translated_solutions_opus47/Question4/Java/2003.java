import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite::memory:");

        try (Statement stmt = db.createStatement()) {
            stmt.execute("CREATE TABLE students (name TEXT, age INT)");
        }

        insertStudent(db, "XXXXX", 0);

        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                System.out.println("{ name: '" + rs.getString("name") + "', age: " + rs.getInt("age") + " }");
            }
        }

        db.close();
    }

    public static void insertStudent(Connection db, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}