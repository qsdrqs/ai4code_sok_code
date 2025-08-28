import java.sql.*;

public class StudentDatabase {
    
    // Define a Java method that takes as input a String name and an int age and adds a new entry to "STUDENTS".
    // The method should return the number of rows affected.
    public static int addStudent(Connection db, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        int result = 0;
        
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            result = pstmt.executeUpdate();
            
            // Print result
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    // Test example
    public static void main(String[] args) {
        Connection db = null;
        
        try {
            // Create sql database connection
            db = DriverManager.getConnection("jdbc:sqlite:database.db");
            
            // Create table (only do this once!)
            // String createTableSql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
            // Statement stmt = db.createStatement();
            // stmt.execute(createTableSql);
            // stmt.close();
            
            addStudent(db, "XXXXX", 0);
            
            // Display contents of database
            String selectSql = "SELECT * FROM STUDENTS";
            Statement stmt = db.createStatement();
            ResultSet rows = stmt.executeQuery(selectSql);
            
            while (rows.next()) {
                System.out.println(rows.getString("NAME") + " " + rows.getInt("AGE"));
            }
            
            rows.close();
            stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}