import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class DatabaseInsertExample {

    public static void main(String[] args) {
        // Database connection details
        String host = "127.0.0.1";
        String database = "mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // The JDBC connection URL string
        // Includes parameters to avoid common issues with SSL and timezones
        String url = String.format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", host, database);

        // The SQL statement for inserting a single record.
        // In JDBC, we use a single `VALUES (?, ?)` and add data in a loop (batching).
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

        // The values to be inserted. This is a 2D array, similar to the JS version.
        String[][] values = {
            {"Peter Griffin", "31 Spooner Street"},
            {"Lois Griffin", "31 Spooner Street"},
            {"Homer Simpson", "742 Evergreen Terrace"}
        };

        // The try-with-resources statement ensures that the connection and statement
        // are automatically closed, even if an error occurs.
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println("Connected to the database successfully!");

            // Loop through the data and add each row to the batch
            for (String[] row : values) {
                pstmt.setString(1, row[0]); // Set the first parameter (name)
                pstmt.setString(2, row[1]); // Set the second parameter (address)
                pstmt.addBatch();           // Add the prepared statement to the batch
            }

            // Execute the batch of statements
            int[] updateCounts = pstmt.executeBatch();

            // Calculate the total number of records inserted
            int totalAffectedRows = Arrays.stream(updateCounts).sum();

            System.out.println("Number of records inserted: " + totalAffectedRows);

        } catch (SQLException e) {
            // In Java, exceptions are caught rather than passed to a callback.
            // We print the stack trace to see what went wrong.
            e.printStackTrace();
        }
    }
}