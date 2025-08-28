import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to an in-memory database
            conn = DriverManager.getConnection("jdbc:sqlite::memory:");

            // Create the students table
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE students (name TEXT, age INT)");
            }

            // Insert a student
            insertStudent(conn, "XXXXX", 0);

            // Query and print all students
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Inserts a student into the students table.
     */
    public static void insertStudent(Connection db, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}