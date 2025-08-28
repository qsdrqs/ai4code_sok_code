import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtils {

    /**
     * Adds a new record to the given table.
     *
     * @param name  value for the “title” column
     * @param age   value for the “completed” column
     * @param table table to insert into (e.g. "todos")
     */
    public static void add(String name, int age, String table) {

        /* === Connection settings (replace with your own) === */
        String url      = "jdbc:mysql://localhost:3306/your_database";
        String user     = "db_user";
        String password = "db_password";
        /* =================================================== */

        // NOTE: table names cannot be used as SQL parameters, so we concatenate
        // them carefully.  Do NOT pass un-trusted values here in real code.
        String sql = "INSERT INTO " + table + " (title, completed) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name); // title
            ps.setInt   (2, age);  // completed (or whatever the column really is)

            int affected = ps.executeUpdate();
            System.out.println("Row inserted: " + affected);

        } catch (SQLException e) {
            // Log the exception or re-throw it depending on your needs
            e.printStackTrace();
        }
    }
}