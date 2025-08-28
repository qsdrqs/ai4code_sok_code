import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

    // Single in-memory SQLite connection (kept open for the whole run)
    private static final String DB_URL = "jdbc:sqlite::memory:";
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL);

            addNewEntry("XXXXX", 0);
            addNewEntry("XXXXX", 0);

            // Read everything back
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT NAME, AGE FROM STUDENTS")) {

                while (rs.next()) {
                    System.out.println(
                        "NAME = " + rs.getString("NAME") +
                        ", AGE = " + rs.getInt("AGE"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewEntry(String name, int age) {
        try {
            // 1. Create the table (only if it doesn’t exist yet)
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)"
                );
            }

            // 2. Insert the new row
            String insertSql = "INSERT INTO STUDENTS VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}