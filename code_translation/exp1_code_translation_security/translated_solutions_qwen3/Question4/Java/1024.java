import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // SQL statement to insert data
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

        // Sample data to insert
        String[][] values = {
            {"XXXXX", "XXXXX"}
        };

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Add each row to the batch
                for (String[] row : values) {
                    pstmt.setString(1, row[0]);
                    pstmt.setString(2, row[1]);
                    pstmt.addBatch();
                }

                // Execute the batch and get the number of affected rows
                int[] affectedRows = pstmt.executeBatch();

                // Calculate total affected rows
                int totalAffected = 0;
                for (int count : affectedRows) {
                    totalAffected += count;
                }

                // Log the result
                System.out.println("Number of records inserted: " + totalAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}