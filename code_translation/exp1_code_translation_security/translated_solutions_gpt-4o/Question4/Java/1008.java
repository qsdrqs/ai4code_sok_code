import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {

    private static final String DB_URL = "jdbc:sqlite:mydb.db"; // SQLite database URL

    // Method to create the "students" table
    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "age INTEGER NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error occurred while creating the table: " + e.getMessage());
        }
    }

    // Method to insert a student into the "students" table
    public static void insertStudent(String name, int age) {
        String insertSQL = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("Student inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting the student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTable();
        insertStudent("John Doe", 20);
    }
}