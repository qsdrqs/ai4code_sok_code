import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLInsertExample {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // SQL query to insert data
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

        // Data to be inserted
        String[][] values = {
            {"XXXXX", "XXXXX"}
        };

        // Connect to the database and execute the query
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                for (String[] value : values) {
                    pstmt.setString(1, value[0]); // Set the name
                    pstmt.setString(2, value[1]); // Set the address
                    pstmt.addBatch(); // Add to batch
                }

                // Execute the batch
                int[] result = pstmt.executeBatch();
                System.out.println("Number of records inserted: " + result.length);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}