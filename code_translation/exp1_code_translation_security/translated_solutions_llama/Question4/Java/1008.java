import java.sql.*;

public class SQLiteDB {
    private static final String DB_NAME = "mydb.db";
    private static final String TABLE_NAME = "students";

    // Method to create a table in the database
    public static void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
             Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error occurred while creating the table: " + e.getMessage());
        }
    }

    // Method to insert a student into the table
    public static void insertStudent(String name, int age) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, age) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("Student inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting the student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create the database and table if it doesn't exist
        createTable();

        // Insert a student into the table
        insertStudent("John Doe", 20);
    }
}