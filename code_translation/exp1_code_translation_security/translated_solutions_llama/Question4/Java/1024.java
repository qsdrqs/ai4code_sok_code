import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Database connection parameters
        String host = "127.0.0.1";
        String user = "yourusername";
        String password = "yourpassword";
        String database = "mydb";

        // SQL query
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

        // Values to be inserted
        String[][] values = {
                {"XXXXX", "XXXXX"}
        };

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":3306/" + database,
                    user,
                    password
            );

            // Create a prepared statement
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Insert values
            int recordsInserted = 0;
            for (String[] value : values) {
                pstmt.setString(1, value[0]);
                pstmt.setString(2, value[1]);
                pstmt.addBatch();
            }

            // Execute the batch update
            int[] results = pstmt.executeBatch();

            // Get the total number of records inserted
            for (int result : results) {
                recordsInserted += result;
            }

            // Print the result
            System.out.println("Number of records inserted: " + recordsInserted);

            // Close the connection
            con.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error interacting with database: " + e.getMessage());
        }
    }
}