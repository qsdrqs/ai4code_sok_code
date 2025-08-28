import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    
    public static void main(String[] args) {
        /*
        Is not TESTED!!
        */
        
        String host = "127.0.0.1";
        String database = "mydb";
        String username = "yourusername";
        String password = "yourpassword";
        
        String url = "jdbc:mysql://" + host + ":3306/" + database;
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            // Connect to your database
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            
            String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
            
            // Values to insert
            String[][] values = {
                {"XXXXX", "XXXXX"}
            };
            
            pstmt = con.prepareStatement(sql);
            
            int totalAffectedRows = 0;
            
            // Insert each row
            for (String[] row : values) {
                pstmt.setString(1, row[0]); // name
                pstmt.setString(2, row[1]); // address
                
                int affectedRows = pstmt.executeUpdate();
                totalAffectedRows += affectedRows;
            }
            
            System.out.println("Number of records inserted: " + totalAffectedRows);
            
        } catch (SQLException err) {
            throw new RuntimeException(err);
        } finally {
            // Clean up resources
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}