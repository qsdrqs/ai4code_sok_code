import java.sql.*;

public class DatabaseManager {
    
    public static void addNewEntry(String name, int age) {
        String url = "jdbc:sqlite:database.db";
        
        try (Connection conn = DriverManager.getConnection(url)) {
            // Insert new entry
            String insertSQL = "INSERT INTO STUDENTS VALUES (?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate();
            }
            
            // Query and display all entries
            String selectSQL = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String studentName = rs.getString("NAME");
                    int studentAge = rs.getInt("AGE");
                    System.out.println(id + ": " + studentName + " " + studentAge);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        addNewEntry("XXXXX", 0);
    }
}