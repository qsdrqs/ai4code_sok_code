import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {

    private static final String DB_URL = "jdbc:sqlite:mydb.db";

    /* Java function to create SQL table "students" with 'name' and 'age' */
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error occurred while creating the table.");
            e.printStackTrace();
        }
    }

    /* Java function to insert string name and an int age to 'students' */
    public static void insertStudent(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}