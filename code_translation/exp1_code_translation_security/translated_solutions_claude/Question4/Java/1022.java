import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private Connection db;
    
    public StudentDatabase() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Create connection to database
            db = DriverManager.getConnection("jdbc:sqlite:/home/codex/Documents/User_Study_UI/database.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void studentWrite(String name, int age) {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void selectAllStudents() {
        String sql = "SELECT * FROM STUDENTS";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                System.out.println("Name: " + name + ", Age: " + age + " | Name: " + name + ", Age: " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (db != null) {
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        StudentDatabase studentDb = new StudentDatabase();
        
        // Insert student record
        studentDb.studentWrite("XXXXX", 0);
        
        // Select and display all students
        studentDb.selectAllStudents();
        
        // Close database connection
        studentDb.closeConnection();
    }
}