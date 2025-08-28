import java.sql.*;

public class StudentDatabase {
    private static Connection db;
    
    static {
        try {
            // Create in-memory SQLite database
            db = DriverManager.getConnection("jdbc:sqlite::memory:");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addNewEntry(String name, int age) {
        try {
            // Create table if not exists
            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
            stmt.close();
            
            // Prepare and execute insert statement
            PreparedStatement pstmt = db.prepareStatement("INSERT INTO STUDENTS VALUES (?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Add entries
        addNewEntry("XXXXX", 0);
        addNewEntry("XXXXX", 0);
        
        // Query and display results
        try {
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME, AGE FROM STUDENTS");
            
            while (rs.next()) {
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                System.out.println("NAME: " + name + ", AGE: " + age);
            }
            
            rs.close();
            stmt.close();
            db.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}