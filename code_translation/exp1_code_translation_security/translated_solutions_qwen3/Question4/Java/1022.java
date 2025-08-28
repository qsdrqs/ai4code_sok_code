import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    public static void main(String[] args) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Database connection URL
            String url = "jdbc:sqlite:/home/codex/Documents/User_Study_UI/database.db";

            // Establish connection to the database
            try (Connection conn = DriverManager.getConnection(url)) {
                // Insert a new student
                studentWrite(conn, "XXXXX", 0);

                // Query and print all students
                String query = "SELECT * FROM STUDENTS";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {

                    while (rs.next()) {
                        String name = rs.getString("NAME");
                        int age = rs.getInt("AGE");
                        System.out.println("Name: " + name + ", Age: " + age);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new student into the STUDENTS table.
     *
     * @param db    the database connection
     * @param name  the name of the student
     * @param age   the age of the student
     * @throws SQLException if a database access error occurs
     */
    static void studentWrite(Connection db, String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}