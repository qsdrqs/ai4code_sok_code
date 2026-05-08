import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExample {
    /*
    Is not TESTED!!
    */
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // connect to your database
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");

            String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
            String[][] values = {
                    {"XXXXX", "XXXXX"},
            };

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                for (String[] value : values) {
                    stmt.setString(1, value[0]);
                    stmt.setString(2, value[1]);
                    stmt.addBatch();
                }

                int[] results = stmt.executeBatch();
                int affectedRows = 0;
                for (int r : results) {
                    affectedRows += r;
                }

                System.out.println("Number of records inserted: " + affectedRows);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}