import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

    // In-memory SQLite URL
    private static final String DB_URL = "jdbc:sqlite::memory:";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            createTable(conn);
            insertStudent(conn, "XXXXX", 0);
            printAllStudents(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE TABLE students (name TEXT, age INT)
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE students (name TEXT, age INT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // INSERT INTO students (name, age) VALUES (?, ?)
    private static void insertStudent(Connection conn, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
        }
    }

    // SELECT * FROM students
    private static void printAllStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    "name = " + rs.getString("name") +
                    ", age = " + rs.getInt("age")
                );
            }
        }
    }
}