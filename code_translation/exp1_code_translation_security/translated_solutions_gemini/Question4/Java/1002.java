import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * This class contains a utility method to insert a user into the database.
 * It assumes an active database connection is passed to it.
 */
public class UserDAO {

    /**
     * Inserts a new user into the STUDENTS table.
     *
     * @param conn An active SQL Server database connection (e.g., from com.microsoft.sqlserver.jdbc.SQLServerDataSource).
     * @param name The name of the student to insert.
     * @param age The age of the student to insert.
     */
    public void insertUser(Connection conn, String name, int age) {
        // The SQL query uses '?' as placeholders for parameters in JDBC.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

        // Use a try-with-resources statement to ensure the PreparedStatement is automatically closed.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the parameters to the '?' placeholders.
            // The first parameter is at index 1.
            // This is equivalent to: request.addParameter("NAME", TYPES.NVarChar, name);
            pstmt.setString(1, name);

            // This is equivalent to: request.addParameter("AGE", TYPES.Int, age);
            pstmt.setInt(2, age);

            // Execute the query. Use executeUpdate() for INSERT, UPDATE, or DELETE statements.
            // This is equivalent to: conn.execSql(request);
            pstmt.executeUpdate();

            System.out.println("User '" + name + "' inserted successfully.");

        } catch (SQLException e) {
            // This is the equivalent of the 'function(err)' callback for handling errors.
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}