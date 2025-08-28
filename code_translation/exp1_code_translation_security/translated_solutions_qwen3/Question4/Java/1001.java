import java.sql.*;

public class StudentDatabase {

    // JDBC URL for an in-memory SQLite database (can be changed to a file path if needed)
    private static final String DB_URL = "jdbc:sqlite::memory:";

    /**
     * Adds a new student entry to the 'students' table and logs the ID of the inserted record.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public static void addSQLentry(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the insert operation
            pstmt.executeUpdate();

            // Retrieve the last inserted row ID
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {

                if (rs.next()) {
                    long lastId = rs.getLong(1);
                    System.out.println("User added with id: " + lastId);
                }
            }

        } catch (SQLException e) {
            // Log any SQL errors
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }
}