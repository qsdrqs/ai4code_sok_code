import java.sql.*;

public class StudentDatabase {
    private static final String DB_URL = "jdbc:sqlite:studentdb";
    
    public static void main(String[] args) {
        Connection db = null;
        
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Create database connection
            db = DriverManager.getConnection(DB_URL);
            
            // Create table if not exists
            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
            stmt.close();
            
            // Insert data
            insert(db, "Foo", 42);
            insert(db, "Bar", 55);
            
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
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
    
    private static void insert(Connection db, String name, int age) {
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)");
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            
            /*
            // Equivalent of the commented JavaScript code for selecting all records
            Statement selectStmt = db.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT * FROM STUDENTS");
            
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
            }
            
            rs.close();
            selectStmt.close();
            */
            
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
        }
    }
}