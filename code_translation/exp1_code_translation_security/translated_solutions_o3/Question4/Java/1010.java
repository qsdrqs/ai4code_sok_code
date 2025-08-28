import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Mirrors the behaviour of the original JavaScript code.
 *
 * 1. Opens/creates an SQLite database file.
 * 2. Optionally creates the STUDENTS table.
 * 3. Adds a student (name, age).
 * 4. Prints out all rows.
 * 5. Closes the connection.
 */
public class StudentDemo {

    /* Connection handle that plays the same role as the JS “db” object. */
    private final Connection connection;

    /**
     * Open (or create) the SQLite database file.
     */
    public StudentDemo(String dbFile) throws SQLException {
        String url = "jdbc:sqlite:" + dbFile;         // e.g. "jdbc:sqlite:./database.db"
        connection = DriverManager.getConnection(url);
    }

    /**
     * Creates the STUDENTS table if it does not yet exist.
     * Equivalent to the commented-out CREATE TABLE command in the JS sample.
     */
    public void createTableIfNeeded() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS STUDENTS ("
                   + "NAME TEXT, "
                   + "AGE  INT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Java counterpart of the JavaScript function “addStudent”.
     *
     * @param name  student name
     * @param age   student age
     * @return      number of rows affected (0 or 1)
     */
    public int addStudent(String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            return ps.executeUpdate();   // number of rows affected
        }
    }

    /**
     * Utility method that prints all rows in STUDENTS, mimicking the JS “db.all(...)”
     * callback that loops over rows and logs them to the console.
     */
    public void printAllStudents() throws SQLException {
        String sql = "SELECT * FROM STUDENTS";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String studentName = rs.getString("NAME");
                int    studentAge  = rs.getInt("AGE");
                System.out.println(studentName + " " + studentAge);
            }
        }
    }

    /**
     * Close the connection (similar to “db.close()” in the JavaScript sample).
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /* ---------------------------------------------------------------------- */
    /* Main – demonstration that parallels the JavaScript test code           */
    /* ---------------------------------------------------------------------- */

    public static void main(String[] args) {
        try {
            // 1. Open / create the database
            StudentDemo demo = new StudentDemo("./database.db");

            // 2. Create table if this is the first run
            demo.createTableIfNeeded();

            // 3. Add a sample student
            int rowsAffected = demo.addStudent("XXXXX", 0);
            System.out.println("Rows affected: " + rowsAffected);

            // 4. Show contents of the table
            demo.printAllStudents();

            // 5. Close database
            demo.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}