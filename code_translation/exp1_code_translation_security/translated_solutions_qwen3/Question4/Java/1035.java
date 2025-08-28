import java.sql.*;

public class StudentDB {

    public static void main(String[] args) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the SQLite database (or create it if it doesn't exist)
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:studentdb")) {

                // Create the STUDENTS table if it doesn't exist
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
                }

                // Insert sample data
                insertData(conn, "Foo", 42);
                insertData(conn, "Bar", 55);

                // Optional: Uncomment to print all students
                /*
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS")) {
                    while (rs.next()) {
                        System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
                    }
                } catch (SQLException e) {
                    System.err.println("Error querying database: " + e.getMessage());
                }
                */

            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
        }
    }

    /**
     * Inserts a student into the STUDENTS table.
     *
     * @param conn the database connection
     * @param name the name of the student
     * @param age  the age of the student
     */
    private static void insertData(Connection conn, String name, int age) {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }
}