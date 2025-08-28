import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simple wrapper that mimics the JavaScript WebSQL example:
 *
 *  • createTable()   – CREATE TABLE IF NOT EXISTS students …
 *  • insertStudent() – INSERT INTO students(name, age) VALUES (?, ?)
 *
 * All external libraries (the JDBC driver for the DB you use – e.g. SQLite,
 * H2, MySQL, PostgreSQL, …) are assumed to be present on the class-path.
 */
public final class StudentDatabase {

    /* EDIT THE URL IF YOU USE A DIFFERENT JDBC BACK-END ------------------- */
    private static final String DB_URL = "jdbc:sqlite:mydb.sqlite";

    /* --------------------------------------------------------------------- */
    /* 1) CREATE  THE  TABLE                                                 */
    /* --------------------------------------------------------------------- */
    public void createTable() {
        final String sql =
            "CREATE TABLE IF NOT EXISTS students (" +
            "  id   INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  name TEXT," +
            "  age  INTEGER" +
            ')';

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement  stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");

        } catch (SQLException ex) {
            System.err.println("Error occurred while creating the table.");
            ex.printStackTrace();
        }
    }

    /* --------------------------------------------------------------------- */
    /* 2) INSERT  A  ROW                                                     */
    /* --------------------------------------------------------------------- */
    public void insertStudent(String name, int age) {
        final String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection        conn  = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt  = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt   (2, age);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error occurred while inserting the student.");
            ex.printStackTrace();
        }
    }

    /* --------------------------------------------------------------------- */
    /* OPTIONAL  DRIVER  LOAD  (modern JDBC drivers auto-register themselves) */
    /* --------------------------------------------------------------------- */
    static {
        try {
            // For SQLite; change if you use a different RDBMS
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ignored) {
            // DriverManager can still discover the driver if it’s on the class-path
        }
    }

    /* --------------------------------------------------------------------- */
    /* QUICK TEST                                                             */
    /* --------------------------------------------------------------------- */
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();

        db.createTable();                 // same as first JS function
        db.insertStudent("Alice", 21);    // same as second JS function
        db.insertStudent("Bob",   18);
    }
}