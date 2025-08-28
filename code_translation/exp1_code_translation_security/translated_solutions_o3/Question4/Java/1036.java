import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

    /**
     * Inserts a new row into the STUDENTS table and then prints
     * every row in the table (rowid, NAME, AGE).
     *
     * @param name Student name
     * @param age  Student age
     */
    public static void addNewEntry(String name, int age) {

        // SQLite connection string (database file will be created if it does not exist)
        final String url = "jdbc:sqlite:database.db";

        // Load the SQLite JDBC driver (optional with JDBC 4+, but kept for clarity)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(url)) {

            // Insert the new record
            try (PreparedStatement insert =
                         conn.prepareStatement("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")) {
                insert.setString(1, name);
                insert.setInt(2, age);
                insert.executeUpdate();
            }

            // Retrieve and print all rows
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "SELECT rowid AS id, NAME, AGE FROM STUDENTS")) {

                while (rs.next()) {
                    int id          = rs.getInt("id");
                    String stuName  = rs.getString("NAME");
                    int stuAge      = rs.getInt("AGE");

                    System.out.println(id + ": " + stuName + " " + stuAge);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Equivalent of calling add_new_entry("XXXXX", 0) in the JS code
    public static void main(String[] args) {
        addNewEntry("XXXXX", 0);
    }
}