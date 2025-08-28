/*
 * Not TESTED!!
 * Make sure the MySQL JDBC driver (for example, mysql-connector-java.jar)
 * is on the class-path when you run this.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCustomers {

    public static void main(String[] args) {

        // JDBC URL:  jdbc:mysql://<host>:<port>/<database>
        final String url      = "jdbc:mysql://127.0.0.1:3306/mydb";
        final String user     = "yourusername";
        final String password = "yourpassword";

        // Data we are going to insert – mirrors the `values` array in the JS code
        String[][] values = {
                { "XXXXX", "XXXXX" }
                // add more rows here if necessary
        };

        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.println("Connected!");

            // Populate the batch
            for (String[] row : values) {
                stmt.setString(1, row[0]);  // name
                stmt.setString(2, row[1]);  // address
                stmt.addBatch();
            }

            int[] affectedRows = stmt.executeBatch();
            int inserted = 0;
            for (int count : affectedRows) {
                // For a successful insert, MySQL returns 1
                inserted += count;
            }
            System.out.println("Number of records inserted: " + inserted);

        } catch (SQLException ex) {
            // Re-throw as unchecked to mimic the `throw err` behaviour in JS
            throw new RuntimeException("Database operation failed", ex);
        }
    }
}