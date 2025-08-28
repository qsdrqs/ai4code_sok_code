import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    public static void main(String[] args) {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to an in-memory SQLite database
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:")) {
                // Add two entries
                addNewEntry(conn, "XXXXX", 0);
                addNewEntry(conn, "XXXXX", 0);

                // Query and print all rows
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT NAME, AGE FROM STUDENTS")) {

                    while (rs.next()) {
                        System.out.println("NAME: " + rs.getString("NAME") + ", AGE: " + rs.getInt("AGE"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new entry to the STUDENTS table.
     *
     * @param conn Database connection
     * @param name Name of the student
     * @param age  Age of the student
     * @throws SQLException if a database access error occurs
     */
    public static void addNewEntry(Connection conn, String name, int age) throws SQLException {
        // Create the table if it doesn't exist
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)");
        }

        // Insert the new student
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STUDENTS VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}