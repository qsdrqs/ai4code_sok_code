import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    public static void main(String[] args) {
        // Create an in-memory SQLite database
        String url = "jdbc:sqlite::memory:";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // Create a new table
                try (Statement stmt = conn.createStatement()) {
                    String createTableSQL = "CREATE TABLE students (name TEXT, age INT)";
                    stmt.execute(createTableSQL);
                }

                // Insert a student
                insertStudent(conn, "XXXXX", 0);

                // Query and print all students
                String selectSQL = "SELECT * FROM students";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSQL)) {

                    while (rs.next()) {
                        System.out.println("Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudent(Connection conn, String name, int age) {
        String insertSQL = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}