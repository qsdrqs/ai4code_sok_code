import java.sql.*;

public class StudentDatabase {
    public static void main(String[] args) {
        Connection db = null;
        
        try {
            // Create in-memory SQLite database
            db = DriverManager.getConnection("jdbc:sqlite::memory:");
            
            // Create table (serialize equivalent - SQLite handles synchronization)
            createTable(db);
            
            // Insert student
            insertStudent(db, "XXXXX", 0);
            
            // Select and display all students
            selectAllStudents(db);
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Close database connection
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database: " + e.getMessage());
                }
            }
        }
    }
    
    private static void createTable(Connection db) throws SQLException {
        String sql = "CREATE TABLE students (name TEXT, age INT)";
        try (Statement stmt = db.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    private static void insertStudent(Connection db, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
    
    private static void selectAllStudents(Connection db) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("Row: name=" + name + ", age=" + age);
            }
        }
    }
}