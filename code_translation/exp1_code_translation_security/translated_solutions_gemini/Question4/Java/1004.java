import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager {

    // Database credentials and connection URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name"; // <-- Specify your database name
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";

    /**
     * Java method addStudent
     * Given a String name and int age, this method adds a new entry to the STUDENTS table.
     * It uses a PreparedStatement to prevent SQL injection.
     *
     * @param con The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     * @throws SQLException if a database access error occurs.
     */
    public static void addStudent(Connection con, String name, int age) throws SQLException {
        // The '?' are placeholders for the values. This is the safe way to build queries.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        System.out.println("Preparing statement: " + sql);

        // A PreparedStatement is pre-compiled and helps prevent SQL injection.
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Bind values to the placeholders
            pstmt.setString(1, name); // The first '?' is at index 1
            pstmt.setInt(2, age);     // The second '?' is at index 2

            // Execute the query and get the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected + " record(s) inserted successfully.");
        }
    }

    public static void main(String[] args) {
        // The try-with-resources statement ensures that the connection is closed automatically.
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            
            System.out.println("Connected to the database successfully!");

            // Call the addStudent method to insert a new student.
            // Note: The original JS code passed a string "0" for age.
            // In strongly-typed Java, we pass an integer.
            addStudent(con, "Jane Doe", 22);
            addStudent(con, "John Smith", 25);

        } catch (SQLException e) {
            // This block catches any SQL errors, similar to `if (err) throw err;`
            System.err.println("Database connection or query execution failed.");
            e.printStackTrace();
        }
    }
}